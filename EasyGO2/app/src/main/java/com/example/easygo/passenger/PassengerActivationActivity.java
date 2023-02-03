package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.easygo.R;
import com.example.easygo.dto.LoginDTO;
import com.example.easygo.dto.LoginResponseDTO;
import com.example.easygo.dto.ResponseMessageDTO;
import com.example.easygo.dto.TokenDTO;
import com.example.easygo.service.ServiceUtilis;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerActivationActivity extends AppCompatActivity {

    private Integer activationCode;
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_activation);

        MaterialButton confirmBtn = (MaterialButton) findViewById(R.id.confirm_button);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activatePassenger();
            }
        });
    }

    private void activatePassenger(){
        this.activationCode = Integer.parseInt(((EditText)findViewById(R.id.activationCode)).getText().toString());
        Call<ResponseMessageDTO> call = ServiceUtilis.userService.activatePassenger(activationCode);
        call.enqueue(new Callback<ResponseMessageDTO>() {
            @Override
            public void onResponse(Call<ResponseMessageDTO> call, Response<ResponseMessageDTO> response) {
                Toast.makeText(PassengerActivationActivity.this, "Successful account activation!", Toast.LENGTH_SHORT).show();
                deleteTokenPreferences();
                String email = getIntent().getStringExtra("EMAIL");
                String password = getIntent().getStringExtra("PASSWORD");
                loginUser(email, password);
            }

            @Override
            public void onFailure(Call<ResponseMessageDTO> call, Throwable t) {

            }
        });
    }

    public void loginUser(String email, String password){
        LoginDTO loginDTO = new LoginDTO(email, password);
        Call<LoginResponseDTO> call = ServiceUtilis.userService.login(loginDTO);
        call.enqueue(new Callback<LoginResponseDTO>() {
            @Override
            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                if(!response.isSuccessful()) return;
                LoginResponseDTO loginResponseDTO = response.body();
                JWT jwt = new JWT(loginResponseDTO.getAccessToken());
                String email = jwt.getClaim("sub").asString(); // email
                String role = jwt.getClaim("role").asString();    // role
                Integer id = jwt.getClaim("id").asInt();
                TokenDTO tokenDTO = TokenDTO.getInstance();
                tokenDTO.setAccessToken(loginResponseDTO.getAccessToken());

                startActivity(new Intent(PassengerActivationActivity.this, PassengerMainActivity.class));
                setSharedPreferences(role, email, id);
                setTokenPreferences(loginResponseDTO.getAccessToken());
            }




            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {

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



}