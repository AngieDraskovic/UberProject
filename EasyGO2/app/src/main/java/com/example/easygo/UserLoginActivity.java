package com.example.easygo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.dto.LoginDTO;
import com.example.easygo.dto.LoginResponseDTO;
import com.example.easygo.dto.TokenDTO;
import com.example.easygo.mockup.MockupDrivers;
import com.example.easygo.mockup.MockupPassengers;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.passenger.PassengerMainActivity;
import com.example.easygo.passenger.PassengerRegisterActivity;
import com.example.easygo.service.ServiceUtilis;
import com.google.android.material.button.MaterialButton;
import com.example.easygo.reciever.MessageReceiver;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {

    private MessageReceiver messageReceiver;
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


      //  registerBroadcastReciever();

        TextView emailEditTxt =  findViewById(R.id.username);
        TextView passwordEditTxt =  findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton signup = (MaterialButton) findViewById(R.id.signbtn);
/*
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
*/

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTokenPreferences();
                String email = emailEditTxt.getText().toString();
                String password = passwordEditTxt.getText().toString();

//                String email = "dejan@gmail.com";
//                String password = "dejan123";
                loginUser(email, password);

            }
        });


        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLoginActivity.this, PassengerRegisterActivity.class));

            }
        });

    }


    public void loginUser(String email, String password){
        LoginDTO loginDTO = new LoginDTO(email, password);
        Call<LoginResponseDTO> call = ServiceUtilis.userService.login(loginDTO);
        call.enqueue(new Callback<LoginResponseDTO>() {
            @Override
            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UserLoginActivity.this, "Wrong username or password. Try again!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.code()==204){                                                                   // mozda treba 200 :D
                    Toast.makeText(UserLoginActivity.this, "email not confirmed! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginResponseDTO loginResponseDTO = response.body();
                String userRole = "";
                JWT jwt = new JWT(loginResponseDTO.getAccessToken());
                String email = jwt.getClaim("sub").asString(); // email
                String role = jwt.getClaim("role").asString();    // role
                Integer id = jwt.getClaim("id").asInt();
                TokenDTO tokenDTO = TokenDTO.getInstance();
                tokenDTO.setAccessToken(loginResponseDTO.getAccessToken());
                Intent intent;

                if(role.equalsIgnoreCase("driver")){
                   // LoggedIn.setDriver(driver);
                    startActivity(new Intent(UserLoginActivity.this, DriverMainActivity.class));
                    setSharedPreferences(role, email, id);
                    setTokenPreferences(loginResponseDTO.getAccessToken());
                }else if(role.equalsIgnoreCase("passenger")){
                   // LoggedIn.setPassenger(passenger)
                    startActivity(new Intent(UserLoginActivity.this, PassengerMainActivity.class));
                    setSharedPreferences(role, email, id);
                    setTokenPreferences(loginResponseDTO.getAccessToken());

                }

            }


            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                Log.d("GRESKAAA" , t.getMessage());
                Toast.makeText(UserLoginActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteTokenPreferences(){
        TokenDTO tokenDTO = TokenDTO.getInstance();
        tokenDTO.setAccessToken(null);
        tokenDTO.setRefreshToken();
        this.preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.clear().apply();
    }

    public void setSharedPreferences(String role, String email, Integer id){
        this.preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("p_role", role);
        editor.putInt("p_id", id);
        editor.putString("p_email", email);
        editor.apply();
    }

    public void setTokenPreferences(String accesToken){
        this.preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("p_accessToken", accesToken);
        editor.apply();
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


    private void registerBroadcastReciever() {
        messageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("message");
        registerReceiver(messageReceiver, filter);
    }

}
