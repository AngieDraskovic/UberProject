package com.example.easygo.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.easygo.R;
import com.example.easygo.passenger.PassengerAccountActivity;
import com.example.easygo.passenger.PassengerInboxActivity;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRideHistoryActivity;
import com.example.easygo.UserLoginActivity;
public class DriverMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

}