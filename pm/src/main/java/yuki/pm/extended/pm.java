package yuki.pm.extended;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Build;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Package Manager
 */
public final class pm {
    /**
     * Get Current Package ID
     *
     * @param context Context
     * @return Package ID
     */
    public static String GetCurrentPackageName(Context context) {
        ApplicationInfo application = context.getApplicationInfo();
        return application.packageName;
    }

    /**
     * Get Current Package Version Code
     *
     * @param context Context
     * @return Version Code
     */
    @Deprecated
    public static int GetCurrentVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GetCurrentPackageName(context), 0).versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get Current Package Version Name
     *
     * @param context Context
     * @return Version Name
     */
    @SuppressWarnings("deprecation")
    public static String GetCurrentVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(GetCurrentPackageName(context), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get Package Version Code
     *
     * @param packageName Package ID
     * @param context     Context
     * @return Version Code
     */
    @Deprecated
    public static int GetPackageVersionCode(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(packageName, 0).versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get Package Version Name
     *
     * @param packageName Package ID
     * @param context     Context
     * @return Version Code
     */
    @SuppressWarnings("deprecation")
    public static String GetPackageVersionName(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Install A New Package
     *
     * @param apkFilePath Package Path
     * @param context     Context
     */
    public static void InstallPackage(Context context, String apkFilePath) {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        context.startActivity(i);
    }

    /**
     * Get SD Card Folder
     *
     * @param context Context
     * @return SD Card Folder
     */
    public static String GetExternalDir(Context context) {
        return android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * Get Package Data Foler
     *
     * @param context Context
     * @return Package Data Folder
     */
    public static String GetDataDir(Context context) {
        return android.os.Environment.getDataDirectory().getAbsolutePath();
    }
    /**
     * Check Permissions
     * @param context Context
     * @param permission  Permission
     * @return Result
     */
    public static int CheckPermission(Context context,String permission){
        PackageManager packageManager = context.getPackageManager();
        int ret=packageManager.checkPermission(permission,GetCurrentPackageName(context));
        return ret;
    }

    /**
     * Get App's Permissions
     * @param context Context
     * @return Result
     */
    @SuppressWarnings("deprecation")
    public static List<PermissionInfo> GetPermissions(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo pack = packageManager.getPackageInfo(GetCurrentPackageName(context),PackageManager.GET_PERMISSIONS);
            String[] permissionStrings = pack.requestedPermissions;
            ArrayList<PermissionInfo> pis=new ArrayList<PermissionInfo>();
            for (String permission:
                 permissionStrings) {
                try {
                    PermissionInfo pi = packageManager.getPermissionInfo(permission, 0);
                    pis.add(pi);
                }
                catch (Exception ex){
                    //DO NOTHING
                }
            }
            return pis;
        } catch (PackageManager.NameNotFoundException e) {
            //DO NOTHING
            return new ArrayList<PermissionInfo>();
        }
    }

    /**
     * Permission Request
     * @param activity Activity
     * @param permissions permission
     * @param requestCode Code
     * @return Result
     * */
    public static boolean RequestPermissions(Activity activity,String[] permissions,int requestCode){
        if(Build.VERSION.SDK_INT>=23) {
            activity.requestPermissions(permissions, requestCode);
            return true;
        }
        else{
            return true;
        }
    }
}
