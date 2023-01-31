package com.example.easygo.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easygo.R;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.users.Driver;
import com.example.easygo.UserLoginActivity;
import com.example.easygo.service.ServiceUtilis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverAccountActivity extends AppCompatActivity {

    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getDriver();

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
    }

    private void setDriverData(Driver driver) {
        String user = driver.getName() + " " + driver.getSurname();

        ((TextView) findViewById(R.id.txtUser)).setText(user);
        ((TextView) findViewById(R.id.txtEmail)).setText(driver.getEmail());
        ((TextView) findViewById(R.id.txtPhone)).setText(driver.getTelephoneNumber());
        ((TextView) findViewById(R.id.txtAddress)).setText(driver.getAddress());
        ((ImageView) findViewById(R.id.profileImg)).setImageResource(driver.getProfilePic());
    }


    public void getDriver(){
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        Call<UserDTO> call = ServiceUtilis.userService.getDriver(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    driver = new Driver(response.body());
                    setDriverData(driver);
                } else {
                    // handle error response
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

            }
        });
    }


}