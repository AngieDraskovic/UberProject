package com.example.easygo.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.core.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.easygo.R;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.users.Driver;
import com.example.easygo.passenger.PassengerAccountActivity;
import com.example.easygo.passenger.PassengerInboxActivity;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRideHistoryActivity;
import com.example.easygo.UserLoginActivity;
import com.example.easygo.RideNotification;
public class DriverMainActivity extends AppCompatActivity {

    public static final String DRIVER_CHANNEL = "Driver channel";
    public static final String DRIVER_ACTION = "";
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createNotificationChannel();   // vazno je odmah napraviti ovaj channel
//        Intent intentService = new Intent(this, DriverMessageService.class);
//        startService(intentService);

        WebView webView = findViewById(R.id.web_view);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("file:///android_asset/index.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/leaflet.html");
        // webView.addJavascriptInterface(new WebAppinterface(), "Android");
        // webView.loadUrl("https://leafletjs.com/examples/quick-start/example.html");
        webView.evaluateJavascript("loadmap();",null);
        webView.evaluateJavascript("console.log('js loaded')",null);
        //create an instance of RideNotification
        RideNotification rideNotification = new RideNotification(this);

        //call the showNotification method on the rideNotification object
        //rideNotification.showNotification("Hello", "This is a test notification.", DriverMainActivity.class);
        //  createDriverNotificationChannel();
        makeNotification();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case
                    R.id.account:
                startActivity(new Intent(DriverMainActivity.this, DriverAccountActivity.class));
                break;
            case
                    R.id.history:
                startActivity(new Intent(DriverMainActivity.this, DriverHistoryActivity.class));
                break;
            case
                    R.id.home:
                startActivity(new Intent(DriverMainActivity.this, DriverMainActivity.class));
                break;
            case
                    R.id.inbox:
                startActivity(new Intent(DriverMainActivity.this, DriverInboxActivity.class));
                break;
            case
                    R.id.logout:
                startActivity(new Intent(DriverMainActivity.this, UserLoginActivity.class));
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Ovo je za sada kopirano iz Vezbe05
        if (alarmManager == null) {
            Intent alarmIntent = new Intent(this, DriverMessageService.class);
            pendingIntent = PendingIntent.getService(this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        }

        /* Na svake tri sekunde izvrsi pendingIntent, a pendingIntent pokrece DriverMessageService.
           Nece biti ni na 3 sekunde, netacno je dosta kad se radi sa malim vrijednostima. Bice tacnije kad bude na 3 minute.
           Kad se hoveruje dole na 3000 to je objasnjeno.  */
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3000, pendingIntent);
    }


    private void makeNotification(){
        // kada si klikne na notifikaciju koja aktivnost da se otvori
        Intent intent = new Intent(this, DriverMainActivity.class);     // ovdje mozda stavis neku drugu aktivnost, koja ce imati sve podatke ove, todo
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // accept button
        Intent acceptIntent = new Intent(this, BroadcastReceiver.class);
        acceptIntent.setAction("ACCEPT");
        PendingIntent acceptPendingIntent = PendingIntent.getBroadcast(this, 0, acceptIntent, 0);

        // decline button zajedno sa reply
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Please enter reason for declining")
                .build();

        Intent replyInput = new Intent(this, DriverMainActivity.class);
        PendingIntent replyPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_baseline_reply_24, "Decline", pendingIntent)
                                                            .addRemoteInput(remoteInput).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id")
                .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
                .setContentTitle("Ride request")
                .setContentText("You have a ride request!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("departure: Svetislava Kasapinovica 33\ndestination: Janka Cmelika 3\nnumber of passengers: 1\n" +
                                "kilometers: 2km\nprice: 500RSD\nlength: 6min"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_baseline_done_24, "Accept", acceptPendingIntent)
                .addAction(action)
                .setAutoCancel(true);           // gasi notifikaciju


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        //notificationId is a unique int for each notification that you must define
        notificationManager.notify(123, builder.build());
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "naziv";
            String description = "opis kanala";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("id", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    /*
        Samo kopirana metoda iz Vezbe05 samo bez if-a jer Anroid studio kaze da je if nepotreban
     */
    private void createDriverNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(DRIVER_CHANNEL, "Notifications", importance);
        channel.setDescription("Driver notification channel");

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}