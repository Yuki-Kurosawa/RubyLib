package jp.ruby.rubylibrary;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import yuki.control.extended.WebViewEx;
import yuki.msg.extended.NotificationController;
import yuki.pm.extended.pm;
import yuki.resource.extended.GsonConvert;
import yuki.resource.extended.StorageIOManager;
import yuki.resource.extended.UIController;
import yuki.tts.extended.TTSComplexController;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://10.1.1.134:8282/Client.html");
        wv.addJavascriptInterface(new TTSComplexController(getApplicationContext(), Locale.SIMPLIFIED_CHINESE), "tts");
        //wv.addJavascriptInterface(new JS(getApplicationContext()), "tts");
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com/"));
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent srv=new Intent("AAA");
        srv.setPackage(getPackageName());
        startService(srv);
        NotificationController.Notify(getApplicationContext(),1,R.mipmap.ic_launcher,pi,"Ticker","Title","Content",Notification.FLAG_NO_CLEAR|Notification.FLAG_AUTO_CANCEL);
        NotificationController.Notify(getApplicationContext(),1,R.mipmap.ic_launcher,pi,"Ticker2","Title2","Content2",Notification.FLAG_NO_CLEAR|Notification.FLAG_AUTO_CANCEL);
        List<PermissionInfo> pis=pm.GetPermissions(getApplicationContext());
        String data="";
        for (PermissionInfo Pi:
             pis) {
            PackageManager packageManager = getPackageManager();
            data+=Pi.name+":"+Pi.loadDescription(packageManager)+"<br/>";
        }
        wv.loadData(data,"text/html","utf-8");
        ActivityCompat.requestPermissions(this,null,0);
    }
}


