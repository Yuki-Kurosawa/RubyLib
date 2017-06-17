package yuki.pm.extended;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

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
}
