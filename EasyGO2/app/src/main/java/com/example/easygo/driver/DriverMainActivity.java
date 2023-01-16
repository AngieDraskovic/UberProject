package com.example.easygo.driver;

import static com.example.easygo.LoggedIn.getDriver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.core.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.webkit.WebView;

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.dto.RideDTOResponse;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.mockup.MockupDrivers;
import com.example.easygo.mockup.MockupRides;
import com.example.easygo.model.Location;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Route;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.users.Driver;
import com.example.easygo.passenger.PassengerAccountActivity;
import com.example.easygo.passenger.PassengerInboxActivity;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRideHistoryActivity;
import com.example.easygo.UserLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

import com.example.easygo.RideNotification;
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
    private Driver driver;
    private Driver driverHTTPPravi;
    private Ride activeRide;
    private RideDTOResponse activeRideHTTP;

    private String departureCoordinates;
    private String destinationCoordinates;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getDriverHTTPPravi();
//        Toast.makeText(DriverMainActivity.this, driverHTTPPravi.getPassword(), Toast.LENGTH_SHORT).show();


//        Intent intentService = new Intent(this, DriverMessageService.class);
//        startService(intentService);

//        createDriverNotificationChannel();
        createNotificationChannel();   // vazno je odmah napraviti ovaj channel
//        Intent intentService = new Intent(this, DriverMessageService.class);
//        startService(intentService);

//        webView = findViewById(R.id.web_view);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("file:///android_asset/index.html");
//        webView.getSettings().setJavaScriptEnabled(true);
        this.driver = MockupDrivers.findDriver("d", "d");
//        webView.loadUrl("file:///android_asset/leaflet.html");
        // webView.addJavascriptInterface(new WebAppinterface(), "Android");
        // webView.loadUrl("https://leafletjs.com/examples/quick-start/example.html");
//        webView.evaluateJavascript("loadmap();",null);
//        webView.evaluateJavascript("console.log('js loaded')",null);
        //create an instance of RideNotification
        RideNotification rideNotification = new RideNotification(this);

        //call the showNotification method on the rideNotification object
        //rideNotification.showNotification("Hello", "This is a test notification.", DriverMainActivity.class);
        //  createDriverNotificationChannel();
        makeNotification();


        class JavaScriptInterface {
            @JavascriptInterface
            public void setDeparture(final String coordinates) { // "["", ""]"
                double latitude = Double.parseDouble(coordinates.split(",")[0].substring(2, coordinates.split(",")[0].length()-1));
                double longitude = Double.parseDouble(coordinates.split(",")[1].substring(1, coordinates.split(",")[1].length()-2));
                activeRide.getRoutes().get(0).getDeparture().setLatitude(latitude);
                activeRide.getRoutes().get(0).getDeparture().setLongitude(longitude);
//                Toast.makeText(DriverMainActivity.this, "Departure: " + coordinates, Toast.LENGTH_SHORT).show();
            }

            @JavascriptInterface
            public void setDestination(final String coordinates) {
                double latitude = Double.parseDouble(coordinates.split(",")[0].substring(2, coordinates.split(",")[0].length()-1));
                double longitude = Double.parseDouble(coordinates.split(",")[1].substring(1, coordinates.split(",")[1].length()-2));
                activeRide.getRoutes().get(0).getDeparture().setLatitude(latitude);
                activeRide.getRoutes().get(0).getDeparture().setLongitude(longitude);
//                Toast.makeText(DriverMainActivity.this, "Destination: " + coordinates, Toast.LENGTH_SHORT).show();
            }
        }
        webView = findViewById(R.id.driver_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(), "Android");
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                showRoute();
                showDeparture();
                showDestination();
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
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3000, pendingIntent);
//        checkForActiveRides();
        checkForActiveRides();
    }


    private void checkForActiveRides() {
        HashMap<Integer, Ride> rides = MockupRides.getRides();

        for (Ride ride : rides.values()) {
            if (driver.equals(ride.getDriver()) && ride.getStatus().equals(RideStatus.ACTIVE)) {
                this.activeRide = ride;
                showRideOnMap();
                return;
            }
        }
    }

    private void showRideOnMap() {
        showDeparture();
        showDestination();
        showRoute();
    }

    private void showDeparture() {
        String departureAddress = activeRide.getRoutes().get(0).getDeparture().getAddress();
//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
                webView.evaluateJavascript("javascript:getDeparture('"+departureAddress+"')", null);
                print("ULAZI U SHOWDEPARTURE");
//            }
//        });
    }

    private void showDestination() {
        String destinationAddress = activeRide.getRoutes().get(0).getDestination().getAddress();
//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
                webView.evaluateJavascript("javascript:getDestination('"+destinationAddress+"')", null);
//            }
//        });
    }

    private void showRoute() {
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
//                double lat1 = activeRide.getRoutes().get(0).getDeparture().getLatitude();
//                double lat2 = activeRide.getRoutes().get(0).getDeparture().getLongitude();
//                double lng1 = activeRide.getRoutes().get(0).getDestination().getLatitude();
//                double lng2 = activeRide.getRoutes().get(0).getDestination().getLongitude();;
//               webView.evaluateJavascript("javascript:addRoute("+lat1+", "+lat2+", "+lng1+", "+lng2+")", null);

                String departure = activeRide.getRoutes().get(0).getDeparture().getAddress();
                String destination = activeRide.getRoutes().get(0).getDestination().getAddress();
                webView.evaluateJavascript("javascript:addRoute(\""+departure+"\", \""+destination+"\")", null);            }

        });
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
        String departure, destination;
        if (activeRide == null) {
            departure = "Augusta Cesarca 8, Novi Sad";
            destination = "Bulevar Cara Lazara 8, Novi Sad";
        } else {
            departure = activeRide.getRoutes().get(0).getDeparture().getAddress();
            destination = activeRide.getRoutes().get(0).getDestination().getAddress();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id")
                .setSmallIcon(R.drawable.ic_baseline_directions_car_24)
                .setContentTitle("Ride request")
                .setContentText("You have a ride request!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("departure: " + departure + "\ndestination: " + destination + "number of passengers: 1\n" +
                                "kilometers: " + "1.7" + "km\nprice: 500RSD\nlength: 3min 30s"))
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


    private void getDriverHTTPPravi() {
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        Call<UserDTO> call = ServiceUtilis.userService.getDriver(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    driverHTTPPravi = new Driver(response.body());
                    setDriverHTTPPravi(driverHTTPPravi);
                    print(driverHTTPPravi.toString());
//                    Toast.makeText(DriverMainActivity.this, driverHTTPPravi.getName(), Toast.LENGTH_SHORT).show();


                    getActiveRide();


                } else {
                    // handle error response
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

            }
        });
    }

    private void getActiveRide() {
        Call<RideDTOResponse> call = ServiceUtilis.rideService.getDriverActiveRide(driverHTTPPravi.getId());
        print("pozvao ride service");
        call.enqueue(new Callback<RideDTOResponse>() {
            @Override
            public void onResponse(Call<RideDTOResponse> call, Response<RideDTOResponse> response) {
                if (response.isSuccessful()) {
                    print("Usao u suuccessful");
                    assert response.body() != null;
                    activeRideHTTP = response.body();
                    print(activeRideHTTP.toString());
                    showRideOnMap();
//                                Toast.makeText(DriverMainActivity.this, activeRideHTTP.setStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RideDTOResponse> call, Throwable t) {
                Toast.makeText(DriverMainActivity.this, "Failovao ride service", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }

    public void setDriverHTTPPravi(Driver driver){
        driverHTTPPravi = driver;
    }


    public void print(String what) {
        System.out.println("ISPIS: " + what);
    }


}

