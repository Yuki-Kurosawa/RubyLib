package yuki.msg.extended;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Akeno on 2017/08/28.
 */

public abstract class YukiPushService extends Service {

    public abstract String GetRemoteEndPoint();
    private Socket socket;
    public abstract int GetIcon();
    public abstract int GetServiceID();

    public void MessagePushed(String msg){
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("msg://msg"));
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationController.Notify(getApplicationContext(),GetServiceID(),GetIcon(),pi,
                "Yuki Push Service","Yuki Push Service",msg, Notification.FLAG_NO_CLEAR|Notification.FLAG_AUTO_CANCEL);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String EndPoint=GetRemoteEndPoint();
                        Log.e("ep",EndPoint);
                        Log.e("eph",EndPoint.split(":")[0]);
                        Log.e("epp",EndPoint.split(":")[1]);
                        socket = new Socket(EndPoint.split(":")[0], Integer.parseInt(EndPoint.split(":")[1]));
                        BufferedReader sin=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String readline;
                        readline=sin.readLine();

                        while(true){
                            readline=sin.readLine();
                            if(readline.isEmpty()){continue;}
                            if(readline==null){
                                continue;
                            }
                            else {
                                MessagePushed(readline);
                            }
                        }
                    }
                    catch(IOException ex) {

                    }
                    catch (Exception ex){

                    }finally {
                        if(socket.isClosed()){

                        }
                        else if(socket.isConnected()){
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        thread.start();
        return Service.START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
