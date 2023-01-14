package com.example.easygo.passenger.rideorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.easygo.FragmentTransition;
import com.example.easygo.R;
import com.example.easygo.UserLoginActivity;
import com.example.easygo.model.Ride;
import com.example.easygo.passenger.PassengerAccountActivity;
import com.example.easygo.passenger.PassengerInboxActivity;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRideHistoryActivity;

public class RideOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Ride ride = new Ride();
        FragmentTransition.to(Fragment1Locations.newInstance(ride), RideOrderActivity.this, false, R.id.fragmentContainer);

        Button locationsFragmentBtn = findViewById(R.id.locationsFragmentBtn);
        locationsFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(Fragment1Locations.newInstance(ride), RideOrderActivity.this, false, R.id.fragmentContainer);
            }
        });

        Button futureFragmentBtn = findViewById(R.id.futureFragmentBtn);
        futureFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(Fragment2Time.newInstance(ride), RideOrderActivity.this, false, R.id.fragmentContainer);

            }
        });

        Button detailsFragmentBtn = findViewById(R.id.detailsFragmentBtn);
        detailsFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(Fragment3Details.newInstance(ride), RideOrderActivity.this, false, R.id.fragmentContainer);

            }
        });

        Button successFragmentBtn = findViewById(R.id.successFragmentBtn);
        successFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(Fragment4Confirm.newInstance(ride), RideOrderActivity.this, false, R.id.fragmentContainer);

            }
        });


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
                startActivity(new Intent(RideOrderActivity.this, PassengerAccountActivity.class));
                break;
            case
                    R.id.history:
                startActivity(new Intent(RideOrderActivity.this, PassengerRideHistoryActivity.class));
                break;
            case
                    R.id.home:
                startActivity(new Intent(RideOrderActivity.this, PassengerMainActivity.class));
                break;
            case
                    R.id.inbox:
                startActivity(new Intent(RideOrderActivity.this, PassengerInboxActivity.class));
                break;
            case
                    R.id.logout:
                startActivity(new Intent(RideOrderActivity.this, UserLoginActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

}