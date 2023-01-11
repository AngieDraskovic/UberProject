package com.example.easygo.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.easygo.R;
import com.example.easygo.UserLoginActivity;
public class PassengerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView webView = findViewById(R.id.web_view);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("file:///android_asset/index.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/leaflet.html");
//        webView.loadUrl("https://leafletjs.com/examples/quick-start/example.html");
//        webView.evaluateJavascript("loadmap();",null);
//        webView.evaluateJavascript("console.log('js loaded')",null);
    }

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

}