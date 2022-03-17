package yuki.msg.extended;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

/**
 * Created by Akeno on 2017/08/28.
 */

public class NotificationController {
    public static void Notify(Context context,String channel_id,int id, int icon, PendingIntent pendingIntent,String ticker, String title, String content, int flags){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder nb = new Notification.Builder(context,channel_id);
            nb.setSmallIcon(icon).setContentIntent(pendingIntent).setTicker(ticker).setContentTitle(title).setContentText(content).setWhen(System.currentTimeMillis());
            Notification notification = nb.build();
            notification.flags = flags;
            nm.notify(id, notification);
        }
        else{
            String des = "101";
            NotificationChannel channel = new NotificationChannel(channel_id, des, NotificationManager.IMPORTANCE_MIN);
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(context,channel_id)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(icon)
                    .setStyle(new Notification.MediaStyle())
                    .setAutoCancel((flags & Notification.FLAG_AUTO_CANCEL) == Notification.FLAG_AUTO_CANCEL)
                    .build();
            notification.flags=flags;
            nm.notify(id, notification);
        }
    }
}
