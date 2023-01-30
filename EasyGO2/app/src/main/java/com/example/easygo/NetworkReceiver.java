package com.example.easygo;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {

    private boolean isConnected = false;
    private final int REQUEST_LOCATION = 200;

    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            isConnected = true;
            Intent loginIntent = new Intent(context, UserLoginActivity.class);
            context.startActivity(loginIntent);
        } else {
            isConnected = false;
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}