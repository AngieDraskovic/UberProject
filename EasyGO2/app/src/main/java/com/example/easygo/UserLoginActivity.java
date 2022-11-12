package com.example.easygo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.mockup.MockupDrivers;
import com.example.easygo.mockup.MockupPassengers;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRegisterActivity;
import com.google.android.material.button.MaterialButton;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        TextView emailEditTxt =  findViewById(R.id.username);
        TextView passwordEditTxt =  findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton signup = (MaterialButton) findViewById(R.id.signbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditTxt.getText().toString();
                String password = passwordEditTxt.getText().toString();

                Passenger passenger;
                Driver driver;
                if ((passenger = MockupPassengers.findPassenger(email, password)) != null) {
                    LoggedIn.setPassenger(passenger);
                    startActivity(new Intent(UserLoginActivity.this, PassengerMainActivity.class));
                }
                else if ((driver = MockupDrivers.findDriver(email, password)) != null) {
                    LoggedIn.setDriver(driver);
                    startActivity(new Intent(UserLoginActivity.this, DriverMainActivity.class));
                }
                else {
                    Toast.makeText(UserLoginActivity.this,"Incorrect email and password!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this, PassengerRegisterActivity.class));

            }

        });

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