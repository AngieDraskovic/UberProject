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
import android.os.Handler;
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
import com.example.easygo.model.Message;
import com.example.easygo.model.Panic;
import com.example.easygo.model.Remark;
import com.example.easygo.model.Ride;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.passenger.rideorder.RideOrderActivity;
import com.example.easygo.service.ServiceUtilis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerMainActivity extends AppCompatActivity {

    // atributiii

    private WebView webView;
    private Button rideOrderBtn;
    private ImageView messageIcon;
    private Button panicButton;
    private Button remarkButton;

    private static Passenger passenger;
    private Ride activeRide;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);
        createNotificationChannel();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        messages = new ArrayList<>();

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
                if (activeRide != null) {
//                    showRoute();
                    showSimulation();
                    showDeparture();
                    showDestination();
                }
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

        panicButton = findViewById(R.id.passengerPanicButton);
        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPanicPopup(activeRide);
            }
        });

        remarkButton = findViewById(R.id.passengerRemarkButton);
        remarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRemarkPopup(activeRide);
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


    private void showActiveRideForm(){
        messageIcon.setVisibility(View.VISIBLE);
        panicButton.setVisibility(View.VISIBLE);
        remarkButton.setVisibility(View.VISIBLE);

        rideOrderBtn.setVisibility(View.GONE);
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
                String text = messageEditText.getText().toString();
                Message message = new Message(text, passenger, activeRide.getDriver(), activeRide.getId());
                createMessage(message);

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


    private void showMessageNotification(Passenger passenger) {
        /* Dobavi konverzaciju izmedju te dvije osobe */
        Driver driver = activeRide.getDriver();
        Conversation conversation = MockupMessages.getConversation(passenger, driver, messages);

        Intent intent = new Intent(this, PassengerMainActivity.class);
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


    private void showPanicPopup(Ride ride) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter panic reason:");

        final EditText messageEditText = new EditText(this);
        messageEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(messageEditText);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String panicReason = messageEditText.getText().toString();
                Panic panic = new Panic(panicReason, passenger.getId(), ride);
                panicRide(ride, panic);
                Toast.makeText(PassengerMainActivity.this, "Ride panic", Toast.LENGTH_SHORT).show();
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

    private void showRemarkPopup(Ride ride) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remark driver:");

        final EditText messageEditText = new EditText(this);
        messageEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(messageEditText);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = messageEditText.getText().toString();
                Remark remark = new Remark(text, LocalDateTime.now(), activeRide.getDriver().getId());
                createRemark(remark);
                Toast.makeText(PassengerMainActivity.this, "Driver remarked", Toast.LENGTH_SHORT).show();
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
//        showRoute();
        showSimulation();
    }

    private void showRoute() {
        String departure = activeRide.getLocations().get(0).getDeparture().getAddress();
        String destination = activeRide.getLocations().get(0).getDestination().getAddress();
        webView.evaluateJavascript("javascript:addRoute(\""+departure+"\", \""+destination+"\")", null);
    }

    private void showSimulation() {
        String departure = activeRide.getLocations().get(0).getDeparture().getAddress();
        String destination = activeRide.getLocations().get(0).getDestination().getAddress();
        webView.evaluateJavascript("javascript:stimulateMovement(\""+departure+"\", \""+destination+"\")", null);
    }

    private void showDeparture() {
        String departureAddress = activeRide.getLocations().get(0).getDeparture().getAddress();
        webView.evaluateJavascript("javascript:getDeparture('"+departureAddress+"')", null);
    }

    private void showDestination() {
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
                    Ride ride = new Ride(response.body());

                    if (ride.getId() != 0 && activeRide == null) {
                        activeRide = ride;
                        getRidePassengers(ride);
                        getRideDriver(ride);
                        showActiveRideForm();
                        showRideOnMap();
                    }

                    if (ride.getId() == 0 && activeRide != null) {
                        makeNotification(activeRide, passenger);
                        recreate();
                    }
                }
            }

            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                Toast.makeText(PassengerMainActivity.this, "Failovao passenger active ride", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getRidePassengers(Ride ride){
        int i = 0;
        for (Passenger passenger : ride.getPassengers()){
            getPassengerById(ride, i);
            i++;
        }
    }


    public void getPassengerById(Ride ride, int i) {
        Call<UserDTO> call = ServiceUtilis.userService.getPassenger(ride.getPassengers().get(i).getId());
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    ride.getPassengers().set(i, new Passenger(response.body()));
                    System.out.println("Pass");
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast.makeText(PassengerMainActivity.this, "Failovao get passengers", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getRideDriver(Ride ride) {
        Call<UserDTO> call = ServiceUtilis.userService.getDriver(ride.getDriver().getId());
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    ride.setDriver(new Driver(response.body()));
                    System.out.println("Pass");
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast.makeText(PassengerMainActivity.this, "Failovao set driver", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void panicRide(Ride ride, Panic panic) {
        Call<Ride> call = ServiceUtilis.rideService.panicRide(panic, ride.getId());
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PassengerMainActivity.this, "Ride panic", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                System.out.println("ISPIS: Failovano panic ride");
            }
        });
    }


    public void getRideMessages(Ride ride) {
        Call<List<Message>> call = ServiceUtilis.messageService.getRideMessages(ride.getId());
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Message> newMessages = response.body();
                    if (newMessages.size() > messages.size()) {
                        System.out.println("new size: " + newMessages.size() + "     SIZE: " + messages.size());
                        messages = newMessages;
                        showMessageNotification(passenger);
                    }
                    messages = newMessages;
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println("ISPIS: Failovano finish ride");
            }
        });
    }


    public void createMessage(Message message) {
        messages.add(message);
        Call<Message> call = ServiceUtilis.messageService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()) {
                    System.out.println("ISPIS: Prosao create message");
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println("ISPIS: Failovano create message");
            }
        });
    }


    public void createRemark(Remark remark) {
        Call<Remark> call = ServiceUtilis.userService.createRemark(remark, remark.getUserId());
        call.enqueue(new Callback<Remark>() {
            @Override
            public void onResponse(Call<Remark> call, Response<Remark> response) {
                if (response.isSuccessful()) {
                    System.out.println("ISPIS: Prosao remark driver");
                }
            }
            @Override
            public void onFailure(Call<Remark> call, Throwable t) {
                System.out.println("ISPIS: Failovano remark driver");
            }
        });
    }

    public static Passenger getCurrentPassenger() {
        return passenger;
    }


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (passenger != null)
                getPassengerActiveRide(passenger);
            if (activeRide != null)
                getRideMessages(activeRide);

            // Schedule the next execution after 10 seconds
            handler.postDelayed(this, 5 * 1000);
        }
    };

    // Start the timer when the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        handler.post(runnable);
    }

    // Stop the timer when the activity stops
    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

}