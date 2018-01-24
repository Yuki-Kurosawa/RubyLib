package jp.ruby.rubylibrary;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import java.util.List;
import java.util.Locale;

import yuki.control.extended.WebViewEx;
import yuki.msg.extended.NotificationController;
import yuki.pm.extended.NetworkManager;
import yuki.pm.extended.pm;
import yuki.tts.extended.TTSComplexController;

import static yuki.pm.extended.NetworkManager.NO_NETWORK;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebViewEx wv = (WebViewEx) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://10.1.1.134:8282/Client.html");
        wv.addJavascriptInterface(new TTSComplexController(getApplicationContext(), Locale.SIMPLIFIED_CHINESE), "tts");
        //wv.addJavascriptInterface(new JS(getApplicationContext()), "tts");
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com/"));
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        int y=NetworkManager.GetNetworkType(MainActivity.this);
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

        ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isConnectedOrConnecting()) {
            int x= mNetworkInfo.getType();
        } else {
            int x= NO_NETWORK;
        }
    }
}


