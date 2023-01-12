package com.example.easygo;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class RideNotification{
    Context context;
    public RideNotification(Context context) {
        this.context = context;
    }
    public void showNotification(String title, String message, Class<?> activity) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
           // notificationChannel.setVibration(new long[]{0, 1000, 500, 1000});
            //notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        Intent intent = new Intent(context, activity);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setAutoCancel(false);
        builder.setContentTitle(title);
        builder.setContentText(message);
       // builder.setSmallIcon(R.drawable.notification_icon);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setWhen(System.currentTimeMillis());
        notificationManager.notify(1, builder.build());
    }

}
