package yuki.msg.extended;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;

/**
 * Created by Akeno on 2017/08/28.
 */

public class NotificationController {
    public static void Notify(Context context,int id, int icon, PendingIntent pendingIntent,String ticker, String title, String content, int flags){
        NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder nb=new Notification.Builder(context);
        nb.setSmallIcon(icon).setContentIntent(pendingIntent).setTicker(ticker).setContentTitle(title).setContentText(content).setWhen(System.currentTimeMillis());
        Notification notification=nb.build();
        notification.flags=flags;
        nm.notify(id,notification);
    }
}
