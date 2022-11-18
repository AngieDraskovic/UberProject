package com.example.easygo.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.R;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, DriverMainActivity.DRIVER_CHANNEL);

        if (intent.getAction().equals("message")) {
            String messageText = intent.getExtras().getString("messageText");

            notification.setSmallIcon(R.drawable.ic_message);
            notification.setContentTitle("New message");
            notification.setContentText(messageText);
            notificationManager.notify(1, notification.build());
        }
    }
}