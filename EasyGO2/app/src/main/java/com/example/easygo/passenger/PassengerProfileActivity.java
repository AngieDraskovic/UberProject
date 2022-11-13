package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easygo.R;
import com.example.easygo.UserLoginActivity;

public class PassengerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);

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
        EditText lastnameEdit = (EditText)findViewById(R.id.et_last_name);
        lastnameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    lastnameEdit.setText("");
                    lastnameEdit.setHint("Last name");
                }
            }
        });

        EditText email = (EditText)findViewById(R.id.et_email);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    email.setText("");
                    email.setHint("Email");
                }
            }
        });

        EditText phone = (EditText)findViewById(R.id.et_contact_no);
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    phone.setText("");
                    phone.setHint("Phone number");
                }
            }
        });


        EditText address = (EditText)findViewById(R.id.et_address);
        address.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    address.setText("");
                    address.setHint("Adress");
                }
            }
        });

        EditText password = (EditText)findViewById(R.id.et_password);
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