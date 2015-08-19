package com.zencher.app.dailysomething;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

/**
 * Created by ª¢ÄË on 2015/8/19.
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private Context alarmReceiverContext;
    private int notificationProvisionalId;
    @Override
    public void onReceive(Context context, Intent intent) {
        alarmReceiverContext = context;
        notificationProvisionalId = intent.getIntExtra("notificationId", 0);
        NotificationManager myNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = prepareNotification();
        myNotification.notify(notificationProvisionalId, notification);
    }
    private Notification prepareNotification(){
        Intent bootIntent =
                new Intent(alarmReceiverContext, Alarm.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(alarmReceiverContext, 0, bootIntent, 0);
        Notification.Builder builder = new Notification.Builder(
                alarmReceiverContext);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker("Time to Write Something!")
                .setContentTitle("HEY!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent);
        Notification.BigPictureStyle pictureStyle =
                new Notification.BigPictureStyle(builder);
        pictureStyle.bigPicture(BitmapFactory.decodeResource(alarmReceiverContext.getResources(), R.drawable.cat));
        return pictureStyle.build();
    }
}
