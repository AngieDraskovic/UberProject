package com.example.easygo.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.R;
import com.example.easygo.model.Rejection;
import com.example.easygo.model.Ride;
import com.example.easygo.service.ServiceUtilis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if (intent.getAction().equals("ACCEPT")) {
            int rideId = intent.getIntExtra("rideId", 0);
            if (rideId != 0){
                acceptRide(rideId);
            }
            notificationManager.cancelAll();
        }
        if (intent.getAction().toUpperCase().equals("DECLINE")) {
            int rideId = intent.getIntExtra("rideId", 0);
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                CharSequence declineReason = remoteInput.getCharSequence("key_text_reply");
                String reason = declineReason.toString();
                Rejection rejection = new Rejection(reason);
                rejectRide(rideId, rejection);
            }
        }
    }


    public void acceptRide(Integer rideId) {
        Call<Ride> call = ServiceUtilis.rideService.acceptRide(rideId);
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    System.out.println("ISPIS: Prosao");
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {

            }
        });
    }



    public void rejectRide(Integer rideId, Rejection rejection) {
        Call<Ride> call = ServiceUtilis.rideService.rejectRide(rejection, rideId);
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    System.out.println("ISPIS: Rejection successfull!");
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {

            }
        });
    }
}