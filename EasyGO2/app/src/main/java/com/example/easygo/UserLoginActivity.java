package com.example.easygo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.passenger.PassengerRegisterActivity;
import com.google.android.material.button.MaterialButton;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        TextView username =  findViewById(R.id.username);
        TextView password =  findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton signup = (MaterialButton) findViewById(R.id.signbtn);

        // Ovdje cemo vjr vrsiti logovanje :D
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin"))
                {
                    //Toast.makeText(MainActivity,"LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                }else{

                    //Toast.makeText(MainActivity,"LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(UserLoginActivity.this, DriverMainActivity.class));
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