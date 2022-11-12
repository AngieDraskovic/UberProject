package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.mockup.MockupPassengers;
import com.example.easygo.model.users.Passenger;
import com.google.android.material.button.MaterialButton;

public class PassengerRegisterActivity extends AppCompatActivity {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String passwordRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);

        MaterialButton signBtn = (MaterialButton) findViewById(R.id.signbtn);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeValues();
                if (validInput()) {
                    int id = MockupPassengers.generateId();
                    Passenger passenger = new Passenger(id, name, surname, -1, phone, email, address, password, false);
                    MockupPassengers.addNew(passenger);
                    LoggedIn.setPassenger(passenger);
                    startActivity(new Intent(PassengerRegisterActivity.this, PassengerMainActivity.class));
                }
            }
        });
    }


    /* Uzima podatke sa forme i smiješta ih u promjenljive tipa String.
       Te String promjenljive su navedene gore na pocetku. */
    private void takeValues() {
        this.name = ((EditText) findViewById(R.id.name)).getText().toString();
        this.surname = ((EditText) findViewById(R.id.surname)).getText().toString();
        this.email = ((EditText) findViewById(R.id.email)).getText().toString();
        this.phone = ((EditText) findViewById(R.id.number)).getText().toString();
        this.address = ((EditText) findViewById(R.id.adress)).getText().toString();
        this.password = ((EditText) findViewById(R.id.password)).getText().toString();
        this.passwordRepeat = ((EditText) findViewById(R.id.passwordRepeat)).getText().toString();
    }


    private boolean validInput() {
        if (name.equals("") || surname.equals("") || phone.equals("") || address.equals("") || password.equals("")) {
            Toast.makeText(PassengerRegisterActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.contains("@")){
            Toast.makeText(PassengerRegisterActivity.this, "Incorrect email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(passwordRepeat)) {
            Toast.makeText(PassengerRegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return false;
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