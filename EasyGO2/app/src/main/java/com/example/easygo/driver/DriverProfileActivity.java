package com.example.easygo.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easygo.R;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.users.Driver;
import com.example.easygo.service.ServiceUtilis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverProfileActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText lastnameEdit;
    private EditText phoneEdit;
    private EditText emailEdit;
    private EditText addressEdit;
    private EditText passwordEdit;
    private ImageView profileIcon;

    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        getDriver();

        nameEdit = (EditText)findViewById(R.id.et_first_name);
        nameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    nameEdit.setText("");
                    nameEdit.setHint("Name");
                }
            }
        });

        lastnameEdit = (EditText)findViewById(R.id.et_last_name);
        lastnameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    lastnameEdit.setText("");
                    lastnameEdit.setHint("Last name");
                }
            }
        });

        emailEdit = (EditText)findViewById(R.id.et_email);
        emailEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    emailEdit.setText("");
                    emailEdit.setHint("Email");
                }
            }
        });

        phoneEdit = (EditText)findViewById(R.id.et_contact_no);
        phoneEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    phoneEdit.setText("");
                    phoneEdit.setHint("Phone number");
                }
            }
        });


        addressEdit = (EditText)findViewById(R.id.et_address);
        addressEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    addressEdit.setText("");
                    addressEdit.setHint("Adress");
                }
            }
        });

        passwordEdit = (EditText)findViewById(R.id.et_password);
        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    passwordEdit.setText("");
                    passwordEdit.setHint("Password");
                }
            }
        });

        TextView backArrow = (TextView)findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverProfileActivity.this, DriverAccountActivity.class));
            }
        });

        profileIcon = findViewById(R.id.profileIcon);

        Button btnUpdate = findViewById(R.id.bt_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driver.setName(nameEdit.getText().toString());
                driver.setSurname(lastnameEdit.getText().toString());
                driver.setTelephoneNumber(phoneEdit.getText().toString());
                driver.setEmail(emailEdit.getText().toString());
                driver.setAddress(addressEdit.getText().toString());
                driver.setPassword(passwordEdit.getText().toString());
                Toast.makeText(DriverProfileActivity.this, "Update successfull!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setDriverData(Driver driver) {
        nameEdit.setText(driver.getName());
        lastnameEdit.setText(driver.getSurname());
        phoneEdit.setText(driver.getTelephoneNumber());
        emailEdit.setText(driver.getEmail());
        addressEdit.setText(driver.getAddress());
        passwordEdit.setText(driver.getPassword());
        profileIcon.setImageResource(driver.getProfilePic());
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