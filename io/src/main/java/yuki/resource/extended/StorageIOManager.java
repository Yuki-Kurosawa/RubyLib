package yuki.resource.extended;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import static yuki.resource.extended.ConsoleSimulator.ERROR;
import static yuki.resource.extended.ConsoleSimulator.INFO;


/**
 * RAW RESOURCE MANAGER
 */
public class StorageIOManager {

    public static String ReadStringFromFileSystem(Context context,String Path,boolean error) throws Exception{
        try {
            File F=new File(Path);
            File D=F.getParentFile();
            if(!D.exists()){
                D.mkdirs();
            }
            if(F.exists()) {
                FileInputStream inputStream = new FileInputStream(F);
                byte[] f=new byte[(int)inputStream.available()];
                inputStream.read(f);
                inputStream.close();
                return new String(f,Charset.forName("UTF-8"));
            }
            else {
                throw new FileNotFoundException();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            if(error) {
                throw ex;
            }
            else{
                return "";
            }
        }
    }

    public  static void WriteStringToFileSystem(Context context,String Path,String data,boolean Rewrite){
        try {
            byte[] f=data.getBytes(Charset.forName("UTF-8"));
            File F=new File(Path);
            File D=F.getParentFile();
            if(!D.exists()){
                D.mkdirs();
            }
            if((!F.exists()) || Rewrite) {
                FileOutputStream outputStream = new FileOutputStream(F, false);
                outputStream.write(f);
                outputStream.flush();
                outputStream.close();
                F=new File(Path);
                F.setExecutable(true);
                F.setWritable(true);
                F.setReadable(true);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public  static void WriteStringToFileSystem(Context context,String Path,String data){
        WriteStringToFileSystem(context,Path,data,false);
    }

    /**
    *  Write RAW Resource File To FileSystem
    */
    public static void WriteResourceToFileSystem(Context context, int ResourceID, String Path,boolean Rewrite){
        InputStream inputStream=context.getResources().openRawResource(ResourceID);
        try {
            byte[] f = new byte[inputStream.available()];
            inputStream.read(f);
            File F=new File(Path);
            File D=F.getParentFile();
            if(!D.exists()){
                D.mkdirs();
            }
            if((!F.exists()) || Rewrite) {
                FileOutputStream outputStream = new FileOutputStream(F, false);
                outputStream.write(f);
                outputStream.flush();
                outputStream.close();
                F=new File(Path);
                F.setExecutable(true);
                F.setWritable(true);
                F.setReadable(true);
            }
        }catch (Exception ex){

        }
    }

    public static void WriteResourceToFileSystem(Context context, int ResourceID, String Path){
        WriteResourceToFileSystem(context,ResourceID,Path,false);
    }

    public static void ForceWriteResourceToFileSystem(Context context, int ResourceID, String Path){
        WriteResourceToFileSystem(context,ResourceID,Path,true);
    }

    public static void ExecuteCommands(String cmd, String[] envp, String BasePath){
        Runtime runtime=Runtime.getRuntime();
        StdIO s=ExecuteCommandsWithIO(cmd,envp,BasePath);
    }

    public static StdIO ExecuteCommandsWithIO(String cmd, String[] envp, String BasePath){
        Runtime runtime=Runtime.getRuntime();
        //ProcessBuilder pb=new ProcessBuilder(cmd).directory(new File(BasePath));
        //pb.environment().putAll(envp);
        try {
            Process p=/*pb.start();*/runtime.exec(cmd,envp,new File(BasePath));
            StdIO s=new StdIO();
            InputStream in=p.getInputStream();
            InputStream err=p.getErrorStream();
            IOThread tIn = new IOThread( new ConsoleSimulator(in,INFO));
            IOThread tErr = new IOThread( new ConsoleSimulator(err,ERROR));
            tIn.start();
            tErr.start();
            p.waitFor();
            tIn.join();
            tErr.join();
            s.Output=tIn.simulator.o;
            s.Error=tErr.simulator.e;
            return s;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return new StdIO();
        }
    }

    public static void ExecuteCommandsNoStd(String cmdWithArgs[],String[] envp,String BasePath){
        Runtime runtime=Runtime.getRuntime();
        //ProcessBuilder pb=new ProcessBuilder(cmdWithArgs).directory(new File(BasePath));
        //pb.environment().putAll(envp);
        try {
            Process p=/*pb.start();*/runtime.exec(cmdWithArgs,envp,new File(BasePath));
            p.waitFor();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static boolean CheckFileExists(String Path){
        File f=new File(Path);
        return f.exists();
    }

    public static boolean CheckFileIsDir(String Path){
        File f=new File(Path);
        return f.isDirectory();
    }
}
