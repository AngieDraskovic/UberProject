package com.example.easygo.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.model.users.Driver;
import com.example.easygo.passenger.PassengerAccountActivity;
import com.example.easygo.passenger.PassengerInboxActivity;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerProfileActivity;
import com.example.easygo.passenger.PassengerRideHistoryActivity;
import com.example.easygo.UserLoginActivity;
public class DriverAccountActivity extends AppCompatActivity {

    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.driver = LoggedIn.getDriver();
        setDriverData();

        LinearLayout editProfile = findViewById(R.id.driverProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile.setBackgroundColor(Color.parseColor("#574A46"));
                Intent intent = new Intent(DriverAccountActivity.this, DriverProfileActivity.class);
                startActivity(intent);
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
                startActivity(new Intent(DriverAccountActivity.this, DriverAccountActivity.class));
                break;
            case
                    R.id.history:
                startActivity(new Intent(DriverAccountActivity.this, DriverHistoryActivity.class));
                break;
            case
                    R.id.home:
                startActivity(new Intent(DriverAccountActivity.this, DriverMainActivity.class));
                break;
            case
                    R.id.inbox:
                startActivity(new Intent(DriverAccountActivity.this, DriverInboxActivity.class));
                break;
            case
                    R.id.logout:
                startActivity(new Intent(DriverAccountActivity.this, UserLoginActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    /*
        Kad se predje na ProfileActivity pa se onda vrati na AccountActivity, i dalje se prikazuju stari podaci.
        Zato je dodato onResume jer ce se svaki put kad se vrati na ovu aktivnost podaci ponovo ucitavati.
     */
    @Override
    protected void onResume() {
        super.onResume();
        setDriverData();
    }

    private void setDriverData() {
        String user = driver.getName() + " " + driver.getSurname();

        ((TextView) findViewById(R.id.txtUser)).setText(user);
        ((TextView) findViewById(R.id.txtEmail)).setText(driver.getEmail());
        ((TextView) findViewById(R.id.txtPhone)).setText(driver.getPhone());
        ((TextView) findViewById(R.id.txtAddress)).setText(driver.getAddress());
        ((ImageView) findViewById(R.id.profileImg)).setImageResource(driver.getProfilePic());
    }


}