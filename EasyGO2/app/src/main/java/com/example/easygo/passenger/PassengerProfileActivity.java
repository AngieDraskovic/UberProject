package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.UserLoginActivity;
import com.example.easygo.driver.DriverProfileActivity;
import com.example.easygo.model.users.Passenger;

public class PassengerProfileActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText lastnameEdit;
    private EditText email;
    private EditText address;
    private EditText phone;
    private EditText password;
    private Passenger passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);
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
    protected void onResume(){
        super.onResume();
        this.passenger = LoggedIn.getPassenger();
        nameEdit = (EditText)findViewById(R.id.et_first_name);
        lastnameEdit = (EditText)findViewById(R.id.et_last_name);
        email = (EditText)findViewById(R.id.et_email);
        phone = (EditText)findViewById(R.id.et_contact_no);
        address = (EditText)findViewById(R.id.et_address);
        password = (EditText)findViewById(R.id.et_password);

        //setujem editTextove sa mockup podacima
        nameEdit.setText(passenger.getName());
        lastnameEdit.setText(passenger.getSurname());
        email.setText(passenger.getEmail());
        phone.setText(passenger.getPhone());
        address.setText(passenger.getAddress());
        password.setText(passenger.getPhone());

        //na fokus se pojavljuje hint a brise se napisano

        EditText nameEdit = (EditText)findViewById(R.id.et_first_name);
        nameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    nameEdit.setText("");
                    nameEdit.setHint("Name");
                }
            }
        });

        lastnameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    lastnameEdit.setText("");
                    lastnameEdit.setHint("Last name");
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    email.setText("");
                    email.setHint("Email");
                }
            }
        });

        phone = (EditText)findViewById(R.id.et_contact_no);
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    phone.setText("");
                    phone.setHint("Phone number");
                }
            }
        });


        address.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    address.setText("");
                    address.setHint("Adress");
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    password.setText("");
                    password.setHint("Password");
                }
            }
        });


        TextView backArrow = (TextView)findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerProfileActivity.this, PassengerAccountActivity.class));
            }
        });


        Button btnUpdate = findViewById(R.id.bt_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passenger.setName(nameEdit.getText().toString());
                passenger.setSurname(lastnameEdit.getText().toString());
                passenger.setPhone(phone.getText().toString());
                passenger.setEmail(email.getText().toString());
                passenger.setAddress(address.getText().toString());
                passenger.setPassword(password.getText().toString());
                Toast.makeText(PassengerProfileActivity.this, "Update successfull!", Toast.LENGTH_SHORT).show();

            }
        });
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