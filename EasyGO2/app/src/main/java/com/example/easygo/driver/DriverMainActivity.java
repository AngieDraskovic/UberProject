package com.example.easygo.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.mockup.MockupRides;
import com.example.easygo.model.Location;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Route;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.users.Driver;
import com.example.easygo.passenger.PassengerAccountActivity;
import com.example.easygo.passenger.PassengerInboxActivity;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRideHistoryActivity;
import com.example.easygo.UserLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

public class DriverMainActivity extends AppCompatActivity {

    public static final String DRIVER_CHANNEL = "Driver channel";
    public static final String DRIVER_ACTION = "";

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    private WebView webView;
    private Driver driver;
    private Ride activeRide;

    private String departureCoordinates;
    private String destinationCoordinates;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.driver = LoggedIn.getDriver();

//        Intent intentService = new Intent(this, DriverMessageService.class);
//        startService(intentService);

        createDriverNotificationChannel();

        class JavaScriptInterface {
            @JavascriptInterface
            public void setDeparture(final String coordinates) { // "["", ""]"
                double latitude = Double.parseDouble(coordinates.split(",")[0].substring(2, coordinates.split(",")[0].length()-1));
                double longitude = Double.parseDouble(coordinates.split(",")[1].substring(1, coordinates.split(",")[1].length()-2));
                activeRide.getRoutes().get(0).getDeparture().setLatitude(latitude);
                activeRide.getRoutes().get(0).getDeparture().setLongitude(longitude);
                Toast.makeText(DriverMainActivity.this, "Departure: " + coordinates, Toast.LENGTH_SHORT).show();
            }

            @JavascriptInterface
            public void setDestination(final String coordinates) {
                double latitude = Double.parseDouble(coordinates.split(",")[0].substring(2, coordinates.split(",")[0].length()-1));
                double longitude = Double.parseDouble(coordinates.split(",")[1].substring(1, coordinates.split(",")[1].length()-2));
                activeRide.getRoutes().get(0).getDestination().setLatitude(latitude);
                activeRide.getRoutes().get(0).getDestination().setLongitude(longitude);
                Toast.makeText(DriverMainActivity.this, "Destination: " + coordinates, Toast.LENGTH_SHORT).show();
            }
        }
        webView = findViewById(R.id.driver_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(), "Android");
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
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
//            Toast.makeText(this, ride.getRoutes().size() + "", Toast.LENGTH_SHORT).show();
            if (driver.equals(ride.getDriver()) && ride.getStatus().equals(RideStatus.ACTIVE)) {
                this.activeRide = ride;
                showRideOnMap(ride);
                return;
            }
        }
    }

    private void showRideOnMap(Ride ride) {
        showDeparture();
        showDestination();
    }

    private void showDeparture() {
        String departureAddress = activeRide.getRoutes().get(0).getDeparture().getAddress();
//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
                webView.evaluateJavascript("getDeparture('"+departureAddress+"')", null);
//            }
//        });
    }

    private void showDestination() {
        String destinationAddress = activeRide.getRoutes().get(0).getDestination().getAddress();
//        webView.setWebViewClient(new WebViewClient() {
//            public void onPageFinished(WebView view, String url) {
                webView.evaluateJavascript("getDestination('"+destinationAddress+"')", null);
//            }
//        });
    }

    private void showRoute(Ride ride) {
        showDeparture();
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

