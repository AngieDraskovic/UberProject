package com.example.easygo.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.core.app.RemoteInput;

import android.content.Context;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.easygo.R;
import com.example.easygo.dto.ride.RideDTOResponse;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.mockup.MockupMessages;
import com.example.easygo.mockup.MockupRides;
import com.example.easygo.model.Message;
import com.example.easygo.model.Panic;
import com.example.easygo.model.Ride;
import com.example.easygo.model.WorkingHours;
import com.example.easygo.model.enumerations.MessaggeType;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.model.users.User;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.UserLoginActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.easygo.RideNotification;
import com.example.easygo.reciever.MessageReceiver;
import com.example.easygo.service.ServiceUtilis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverMainActivity extends AppCompatActivity {

    public static final String DRIVER_CHANNEL = "Driver channel";
    public static final String DRIVER_ACTION = "";
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    private WebView webView;
    private ImageView messageIcon;
    private Button panicButton;
    private Button finishRideButton;
    private ToggleButton toggleActive;

    private Driver driver;
    public static List<Ride> driverNextRides;
    private Ride activeRide;
    private RideDTOResponse activeRideHTTP;

    private String departureCoordinates;
    private String destinationCoordinates;

    private List<Message> messages;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggleActive = findViewById(R.id.toggleActive);

        messages = new ArrayList<>();

        messageIcon = findViewById(R.id.message_icon);
        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessageInput();
            }
        });

        panicButton = findViewById(R.id.driverPanicButton);
        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPanicPopup(activeRide);
            }
        });

        finishRideButton = findViewById(R.id.driverFinishRideButton);
        finishRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishRide(activeRide);
            }
        });

        ToggleButton toggleActive = findViewById(R.id.toggleActive);
        toggleActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    createWorkingHour(driver);
                } else {
                    endWorkingHour(driver);
                    // handle toggle button being unchecked
                }
            }
        });

        getDriverHTTP();    // Dobavljamo trenutno ulogovanog drivera

        createNotificationChannel();   // vazno je odmah napraviti ovaj channel
        RideNotification rideNotification = new RideNotification(this);
//        makeNotification();


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


        webView = findViewById(R.id.driver_web_view);
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
                if (toggleActive.isChecked())
                    toggleActive.setChecked(false);
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

    }


    private void checkForActiveRides() {
        HashMap<Integer, Ride> rides = MockupRides.getRides();

        for (Ride ride : rides.values()) {
            if (driver.equals(ride.getDriver()) && ride.getStatus().equals(RideStatus.ACTIVE)) {
                this.activeRide = ride;
//                showActiveRideForm();
                messageIcon.setVisibility(View.VISIBLE);
                showRideOnMap();
                return;
            }
        }
    }

    private void showActiveRideForm(){
        messageIcon.setVisibility(View.VISIBLE);
        panicButton.setVisibility(View.VISIBLE);
        finishRideButton.setVisibility(View.VISIBLE);

        toggleActive.setVisibility(View.GONE);
    }

    private void showRideOnMap() {
        showDeparture();
        showDestination();
//        showRoute();
        showSimulation();
    }

    private void showDeparture() {
        String departureAddress = activeRide.getLocations().get(0).getDeparture().getAddress();
        webView.evaluateJavascript("javascript:getDeparture('"+departureAddress+"')", null);
    }

    private void showDestination() {
        String destinationAddress = activeRide.getLocations().get(0).getDestination().getAddress();
        webView.evaluateJavascript("javascript:getDestination('"+destinationAddress+"')", null);
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


    private void makeNotification(Ride ride){
        // kada si klikne na notifikaciju koja aktivnost da se otvori
        Intent intent = new Intent(this, DriverMainActivity.class);     // ovdje mozda stavis neku drugu aktivnost, koja ce imati sve podatke ove, todo
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // accept button
        Intent acceptIntent = new Intent(this, MessageReceiver.class);
        acceptIntent.setAction("ACCEPT");
        acceptIntent.putExtra("rideId", ride.getId());
        PendingIntent acceptPendingIntent = PendingIntent.getBroadcast(this, 0, acceptIntent, 0);

        // decline button zajedno sa reply
        Intent declineIntent = new Intent(this, MessageReceiver.class);
        declineIntent.putExtra("rideId", ride.getId());

        RemoteInput remoteInput = new RemoteInput.Builder("rejectionReason").setLabel("Please enter reason for declining").build();
        Intent rejectionIntent = new Intent(this, MessageReceiver.class);
        rejectionIntent.putExtra("rideId", ride.getId());
        PendingIntent rejectionPendingIntent = PendingIntent.getBroadcast(this, 0, rejectionIntent, 0);
        NotificationCompat.Action rejectionAction = new NotificationCompat.Action.Builder(R.drawable.ic_baseline_reply_24,
                "Decline", rejectionPendingIntent).addRemoteInput(remoteInput).build();

        PendingIntent declinePendingIntent = PendingIntent.getBroadcast(this, 0, declineIntent, 0);



        String departure, destination;
        departure = ride.getLocations().get(0).getDeparture().getAddress();
        destination = ride.getLocations().get(0).getDestination().getAddress();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id")
                .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
                .setContentTitle("Ride request")
                .setContentText("You have a ride request!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("departure: " + departure + "\ndestination: " + destination + "\nnumber of passengers: " + ride.getPassengers().size() +  "\n" +
                                "kilometers: " + "1.7" + "km\nprice: 500RSD\nlength: 3min 30s"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_baseline_done_24, "Accept", acceptPendingIntent)
                .addAction(rejectionAction);


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


    /* Ova metoda prikazuje popup za slanje poruke */
    private void showMessageInput() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Message:");

        final EditText messageEditText = new EditText(this);
        messageEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(messageEditText);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = messageEditText.getText().toString();
                for (Passenger passenger : activeRide.getPassengers()){
                    Message message = new Message(text, driver, passenger, activeRide.getId());
                    createMessage(message);

                }
                Toast.makeText(DriverMainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
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

    private void showPanicPopup(Ride ride){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter panic reason:");

        final EditText messageEditText = new EditText(this);
        messageEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(messageEditText);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String panicReason = messageEditText.getText().toString();
                Panic panic = new Panic(panicReason, driver.getId(), ride);
                panicRide(ride, panic);
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

    private void showMessageNotification(Driver driver) {
        /* Dobavi konverzaciju izmedju te dvije osobe */
        Passenger passenger = activeRide.getPassengers().get(0);
        Conversation conversation = MockupMessages.getConversation(driver, passenger, messages);

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


    private void getDriverHTTP() {
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        Call<UserDTO> call = ServiceUtilis.userService.getDriver(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    driver = new Driver(response.body());
                    createWorkingHour(driver);
                    getDriverActiveRide(driver);
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this, "Failovao", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDriverActiveRide(Driver driver){
        Call<Ride> call = ServiceUtilis.rideService.getDriverActiveRide(driver.getId());
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Ride ride = new Ride(response.body());

                    if (ride.getId() != 0 && activeRide == null) {
                        activeRide = ride;
                        activeRide.setDriver(driver);
                        getRidePassengers(ride);
                        getRideMessages(ride);
                        showActiveRideForm();
                        showRideOnMap();
                    }

                    if (ride.getId() == 0 && activeRide != null) {
                        recreate();
                    }

//                    else {
//                        getDriverNextRides(driver);
//                    }

                }
            }

            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this, "Failovao driver active ride", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDriverNextRides(Driver driver) {
        Call<List<Ride>> call = ServiceUtilis.rideService.getDriverNextRides(driver.getId());
        call.enqueue(new Callback<List<Ride>>() {
            @Override
            public void onResponse(Call<List<Ride>> call, Response<List<Ride>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    driverNextRides = response.body();
                    for (Ride ride : driverNextRides) {
                        if (ride.getStatus().equals(RideStatus.PENDING)){
                            makeNotification(ride);
                        }
                    }
                    print(""+driverNextRides.size());
                }
            }

            @Override
            public void onFailure(Call<List<Ride>> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this, "Failovao ride service", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createWorkingHour(Driver driver){
        WorkingHours workingHours = new WorkingHours();
        workingHours.setStart(LocalDateTime.now());

        Call<WorkingHours> call = ServiceUtilis.driverService.createWorkingHour(workingHours, driver.getId());
        call.enqueue(new Callback<WorkingHours>() {
            @Override
            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(DriverMainActivity.this,"Create working-hour", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<WorkingHours> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this,"Failovao working-hour", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void endWorkingHour(Driver driver){
        Call<WorkingHours> call = ServiceUtilis.driverService.getDriverActiveWorkingHour(driver.getId());
        call.enqueue(new Callback<WorkingHours>() {
            @Override
            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    WorkingHours activeWorkingHour = response.body();
                    updateActiveWorkingHour(activeWorkingHour);
                    Toast.makeText(DriverMainActivity.this,"Get active working-hour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WorkingHours> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this,"Failovao working-hour", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }


    public void updateActiveWorkingHour(WorkingHours activeWorkingHour){
        activeWorkingHour.setEnd(LocalDateTime.now());
        Call<WorkingHours> call = ServiceUtilis.driverService.updateWorkingHour(activeWorkingHour, activeWorkingHour.getId());
        call.enqueue(new Callback<WorkingHours>() {
            @Override
            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(DriverMainActivity.this,"Ended working-hour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WorkingHours> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this,"Failovao end working-hour", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }


    public void panicRide(Ride ride, Panic panic) {
        Call<Ride> call = ServiceUtilis.rideService.panicRide(panic, ride.getId());
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DriverMainActivity.this, "Ride panic", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                System.out.println("ISPIS: Failovano panic ride");
            }
        });
    }


    public void finishRide(Ride ride) {
        Call<Ride> call = ServiceUtilis.rideService.finishRide(ride.getId());
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DriverMainActivity.this, "Ride finished", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                System.out.println("ISPIS: Failovano finish ride");
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
                        showMessageNotification(driver);
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
                Toast.makeText(DriverMainActivity.this, "Failovao", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void print(String what) {
        System.out.println("ISPIS: " + what);
    }

    public static Ride getNextRide(){
        return driverNextRides.get(0);
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (driver != null){
                getDriverNextRides(driver);
                getDriverActiveRide(driver);
                if (activeRide != null)
                    getRideMessages(activeRide);
            }


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
