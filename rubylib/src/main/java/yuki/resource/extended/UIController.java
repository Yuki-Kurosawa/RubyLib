package yuki.resource.extended;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;

/**
 * Created by Akeno on 2017/06/29.
 */

public class UIController {

    public static void SetStatusBarBackgroundColor(Activity activity,int a,int r,int g,int b){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(Color.argb(a, r, g, b));
        }
    }

    public static void SetStatusBarBackgroundColor(Activity activity,@ColorInt  int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static void SetNavigationBarBackgroundColor(Activity activity,int a,int r,int g,int b){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setNavigationBarColor(Color.argb(a, r, g, b));
        }
    }

    public static void SetNavigationBarBackgroundColor(Activity activity,@ColorInt  int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setNavigationBarColor(color);
        }
    }

    public static DisplayMetrics GetRealDisplayMetrics(Activity activity){
        DisplayMetrics dm=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        return dm;
    }

    public static DisplayMetrics GetLogicalDisplayMetrics(Activity activity){
        DisplayMetrics dm=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static int GetRealDisplayHeight(Activity activity){
        return GetRealDisplayMetrics(activity).heightPixels;
    }

    public static int GetRealDisplayWidth(Activity activity){
        return GetRealDisplayMetrics(activity).widthPixels;
    }

    public static int GetLogicalDisplayHeight(Activity activity){
        return GetLogicalDisplayMetrics(activity).heightPixels;
    }

    public static int GetLogicalDisplayWidth(Activity activity){
        return GetLogicalDisplayMetrics(activity).widthPixels;
    }

    public static int PixelToDP(int pixel,float ratio){
        return (int) (pixel/ratio);
    }

    public static int DPToPixel(int dp,float ratio){
        return (int)(dp*ratio);
    }

    public static DisplayMetrics SizeToDisplayMetrics(int height,int width){
        DisplayMetrics dm=new DisplayMetrics();
        dm.setToDefaults();
        dm.widthPixels=width;
        dm.heightPixels=height;
        return dm;
    }

    public static void HideStatusBar(Activity activity){
        View mDecorView=activity.getWindow().getDecorView();
        int uiv=mDecorView.getSystemUiVisibility();
        uiv|=View.SYSTEM_UI_FLAG_IMMERSIVE;
        uiv|=View.SYSTEM_UI_FLAG_FULLSCREEN;
        mDecorView.setSystemUiVisibility(uiv);
    }

    public static void ShowStatusBar(Activity activity){
        View mDecorView=activity.getWindow().getDecorView();
        int uiv=mDecorView.getSystemUiVisibility();
        uiv|=View.SYSTEM_UI_FLAG_IMMERSIVE;
        uiv&=~View.SYSTEM_UI_FLAG_FULLSCREEN;
        mDecorView.setSystemUiVisibility(uiv);
    }

    public static void HideNavigationBar(Activity activity){
        View mDecorView=activity.getWindow().getDecorView();
        int uiv=mDecorView.getSystemUiVisibility();
        uiv|=View.SYSTEM_UI_FLAG_IMMERSIVE;
        uiv|=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mDecorView.setSystemUiVisibility(uiv);
    }

    public static void ShowNavigationBar(Activity activity){
        View mDecorView=activity.getWindow().getDecorView();
        int uiv=mDecorView.getSystemUiVisibility();
        uiv|=View.SYSTEM_UI_FLAG_IMMERSIVE;
        uiv&=~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mDecorView.setSystemUiVisibility(uiv);
    }
}
