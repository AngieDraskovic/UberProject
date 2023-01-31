package com.example.easygo.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.easygo.R;
import com.example.easygo.UserLoginActivity;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.mockup.MockupMessages;
import com.example.easygo.mockup.MockupRides;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.Ride;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.passenger.rideorder.RideOrderActivity;
import com.example.easygo.service.ServiceUtilis;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerMainActivity extends AppCompatActivity {


    private Ride activeRide;
    private WebView webView;
    private Button rideOrderBtn;
    private ImageView messageIcon;

    private Passenger passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);
        createNotificationChannel();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPassenger();

        class JavaScriptInterface {
            @JavascriptInterface
            public void setDeparture(final String coordinates) { // "["", ""]"
                double latitude = Double.parseDouble(coordinates.split(",")[0].substring(2, coordinates.split(",")[0].length()-1));
                double longitude = Double.parseDouble(coordinates.split(",")[1].substring(1, coordinates.split(",")[1].length()-2));
                activeRide.getLocations().get(0).getDeparture().setLatitude(latitude);
                activeRide.getLocations().get(0).getDeparture().setLongitude(longitude);
//                Toast.makeText(DriverMainActivity.this, "Departure: " + coordinates, Toast.LENGTH_SHORT).show();
            }

            @JavascriptInterface
            public void setDestination(final String coordinates) {
                double latitude = Double.parseDouble(coordinates.split(",")[0].substring(2, coordinates.split(",")[0].length()-1));
                double longitude = Double.parseDouble(coordinates.split(",")[1].substring(1, coordinates.split(",")[1].length()-2));
                activeRide.getLocations().get(0).getDeparture().setLatitude(latitude);
                activeRide.getLocations().get(0).getDeparture().setLongitude(longitude);
//                Toast.makeText(DriverMainActivity.this, "Destination: " + coordinates, Toast.LENGTH_SHORT).show();
            }
        }

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(), "Android");
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
//                showRoute();
//                showDeparture();
//                showDestination();
            }
        });
        webView.loadUrl("file:///android_asset/leaflet.html");
        webView.evaluateJavascript("loadmap();",null);
        webView.evaluateJavascript("console.log('js loaded')",null);

        /* Dodali smo dugme na pocetnu stranicu passengera i klikom na to dugme pokrecemo proces narucivanja */
        rideOrderBtn = findViewById(R.id.rideOrderBtn);
        rideOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerMainActivity.this, RideOrderActivity.class));
            }
        });

        messageIcon = findViewById(R.id.passenger_message_icon);
        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessageInput();
            }
        });

//        makeNotification();
//        checkForActiveRides();
    }

    private void checkForActiveRides() {
        HashMap<Integer, Ride> rides = MockupRides.getRides();
        for (Ride ride : rides.values())
            for (Passenger passenger : ride.getPassengers())
                if (this.passenger.equals(passenger) && ride.getStatus().equals(RideStatus.ACTIVE)) {
                    this.activeRide = ride;
                    messageIcon.setVisibility(View.VISIBLE);
                    rideOrderBtn.setVisibility(View.GONE);
                    showRideOnMap();
                    return;
                }
    }


    /* Ova metoda prikazuje popup za slanje poruke */
    private void showMessageInput() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Message");

        final EditText messageEditText = new EditText(this);
        messageEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(messageEditText);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newMessage = messageEditText.getText().toString();
                MockupMessages.createMessage(newMessage, passenger, activeRide.getDriver(), activeRide);
                showMessageNotification();  // salje notifikaciju driveru
                Toast.makeText(PassengerMainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showMessageNotification() {
        /* Dobavi konverzaciju izmedju te dvije osobe */
        Conversation conversation = MockupMessages.getConversation(passenger, activeRide.getDriver());

        Intent intent = new Intent(this, DriverMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id")
                .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
                .setContentTitle("Message notification")
                .setContentText("Your ride is over! Tap to grade your ride!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(conversation + "\n"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);           // gasi notifikaciju

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(123, builder.build());
    }

//    private void makeNotification(){
//        Intent intent = new Intent(this, PassengerGradeRideActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id")
//                .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
//                .setContentTitle("Ride notification")
//                .setContentText("Your ride is over! Tap to grade your ride!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);           // gasi notifikaciju
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(123, builder.build());
//    }


    @Override
    protected void onResume() {
        super.onResume();
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
                startActivity(new Intent(PassengerMainActivity.this, PassengerAccountActivity.class));
                break;
            case
                    R.id.history:
                startActivity(new Intent(PassengerMainActivity.this, PassengerRideHistoryActivity.class));
                break;
            case
                    R.id.home:
                startActivity(new Intent(PassengerMainActivity.this, PassengerMainActivity.class));
                break;
            case
                    R.id.inbox:
                startActivity(new Intent(PassengerMainActivity.this, PassengerInboxActivity.class));
                break;
            case
                    R.id.logout:
                startActivity(new Intent(PassengerMainActivity.this, UserLoginActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private void makeNotification(Ride ride, Passenger passenger){
        Intent intent = new Intent(this, PassengerGradeRideActivity.class);
        intent.putExtra("ride", ride.getId());
        intent.putExtra("passenger", passenger.getId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id")
                .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
                .setContentTitle("Ride notification")
                .setContentText("Your ride is over! Tap to grade your ride!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);           // gasi notifikaciju

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
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
            if (MockupRides.getRides().size() == 4) this.activeRide = MockupRides.getRides().get(4);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showRideOnMap() {
        showDeparture();
        showDestination();
        showRoute();
    }

    private void showRoute() {
        if (activeRide == null)
            return;
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                String departure = activeRide.getLocations().get(0).getDeparture().getAddress();
                String destination = activeRide.getLocations().get(0).getDestination().getAddress();
                webView.evaluateJavascript("javascript:addRoute(\""+departure+"\", \""+destination+"\")", null);            }
        });
    }

    private void showDeparture() {
        if (activeRide == null)
            return;
        String departureAddress = activeRide.getLocations().get(0).getDeparture().getAddress();
        webView.evaluateJavascript("javascript:getDeparture('"+departureAddress+"')", null);
    }

    private void showDestination() {
        if (activeRide == null)
            return;
        String destinationAddress = activeRide.getLocations().get(0).getDestination().getAddress();
        webView.evaluateJavascript("javascript:getDestination('"+destinationAddress+"')", null);
    }

    public void getPassenger(){
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        Call<UserDTO> call = ServiceUtilis.userService.getPassenger(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    passenger = new Passenger(response.body());
                    getPassengerActiveRide(passenger);

                    Toast.makeText(PassengerMainActivity.this, passenger.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    // handle error response
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

            }
        });
    }


    public void getPassengerActiveRide(Passenger passenger){
        Call<Ride> call = ServiceUtilis.rideService.getPassengerActiveRide(passenger.getId());
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    activeRide = new Ride(response.body());

                    if (activeRide.getId() != 0) {
                        // Za sada tu aktivnu voznju ocjenjujemo, kad (ako) budemo imali pravo, nakon sto voznja zavrsi salje se notif

                        makeNotification(activeRide, passenger);

                        // TODO: Show it on on map
                    }


                } else {
                    // handle error response
                }
            }

            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                Toast.makeText(PassengerMainActivity.this, "Failovao passenger active ride", Toast.LENGTH_SHORT).show();
            }
        });
    }

}