package com.example.easygo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;
import android.window.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    private static final int LOCATION_REQUEST_CODE = 100;
    private BroadcastReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> checkLocationAndNetwork(), 5000);

    }

    private void checkLocationAndNetwork() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isLocationEnabled && isConnected) {
            startActivity(new Intent(this, UserLoginActivity.class));
            finish();
        } else if (!isConnected) {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
            check();
        } else if (!isLocationEnabled) {
            askToTurnOnLocation();
        }
    }

    private void askToTurnOnLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your location is turned off. Please turn it on to continue using the app")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(locationIntent, LOCATION_REQUEST_CODE);
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    finish();
                    dialog.cancel();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            checkLocationAndNetwork();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void check(){

    }
    @Override
    protected  void onResume(){
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

                networkChangeReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        checkLocationAndNetwork();
                    }
                };

                registerReceiver(networkChangeReceiver, intentFilter);
            }
        }
        , 5000);

    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(networkChangeReceiver);
        super.onDestroy();
    }
}
