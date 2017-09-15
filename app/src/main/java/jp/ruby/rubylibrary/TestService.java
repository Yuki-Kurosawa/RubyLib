package jp.ruby.rubylibrary;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.MemoryFile;

import java.io.IOException;

import yuki.msg.extended.NotificationController;
import yuki.msg.extended.YukiPushService;

/**
 * Created by Akeno on 2017/08/28.
 */

public class TestService extends YukiPushService {
    @Override
    public String GetRemoteEndPoint() {
        return "10.1.1.134:8134";
    }

    @Override
    public int GetIcon(){
        return R.mipmap.ic_launcher;
    }
    @Override
    public int GetServiceID(){
        return 10;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void MessagePushed(String msg) {
        Intent j=new Intent(Intent.ACTION_VIEW, Uri.parse(msg));
        j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(j);
        /*Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("msg://msg"));
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationController.Notify(getApplicationContext(),GetServiceID(),GetIcon(),pi,
                "Yuki Push Service","Yuki Push Service",msg, Notification.FLAG_NO_CLEAR|Notification.FLAG_AUTO_CANCEL);*/
    }
}
