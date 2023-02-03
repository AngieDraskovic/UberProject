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

        if (RemoteInput.getResultsFromIntent(intent) != null){
            CharSequence rejectionReason = RemoteInput.getResultsFromIntent(intent).getCharSequence("rejectionReason");
            Rejection rejection = new Rejection((String) rejectionReason);

            int rideId = DriverMainActivity.getNextRide().getId();
            rejectRide(rideId, rejection);
            System.out.println("ISPIS: " + rejectionReason);
        }

        if (intent.getAction() != null && intent.getAction().equals("message")) {
            String messageText = intent.getExtras().getString("messageText");

            notification.setSmallIcon(R.drawable.ic_message);
            notification.setContentTitle("New message");
            notification.setContentText(messageText);
            notificationManager.notify(1, notification.build());
        }

        if (intent.getAction() != null && intent.getAction().equals("ACCEPT")) {
            int rideId = DriverMainActivity.getNextRide().getId();
            if (rideId != 0){
                startRide(rideId);
            }
            notificationManager.cancelAll();

            /* Ponovo pokreni DriverMainActivity */
            Intent intent1 = new Intent(context, DriverMainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
        if (intent.getAction() != null && intent.getAction().toUpperCase().equals("DECLINE")) {
            int rideId = intent.getIntExtra("rideId", 0);
            Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
            if (remoteInput != null) {
                CharSequence declineReason = remoteInput.getCharSequence("key_text_reply");
                String reason = declineReason.toString();
                Rejection rejection = new Rejection(reason);
                rejectRide(2, rejection);

                Intent intent1 = new Intent(context, DriverMainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
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


    public void startRide(Integer rideId) {
        Call<Ride> call = ServiceUtilis.rideService.startRide(rideId);
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {

                    System.out.println("ISPIS: Zapoceta voznja");
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                System.out.println("ISPIS: Failovano start ride");
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