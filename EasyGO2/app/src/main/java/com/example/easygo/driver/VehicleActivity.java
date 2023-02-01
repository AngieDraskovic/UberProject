package com.example.easygo.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.easygo.R;
import com.example.easygo.dto.ride.VehicleDTO;
import com.example.easygo.model.Vehicle;
import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Driver;
import com.example.easygo.service.ServiceUtilis;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleActivity extends AppCompatActivity {

    private EditText model;
    private EditText plate;
    private EditText seats;
    private Spinner vehice_spinner;
    private CheckBox baby_transport;
    private CheckBox pet_transport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        getVehicle();
        model = (EditText) findViewById(R.id.model);
        plate = (EditText) findViewById(R.id.plate);
        seats = (EditText) findViewById(R.id.seats);
        vehice_spinner = (Spinner) findViewById(R.id.vehicle_spinner);
        baby_transport = (CheckBox) findViewById(R.id.baby_transport_checkbox);
        pet_transport = (CheckBox) findViewById(R.id.pet_transport_checkbox);

        MaterialButton btnUpdate = findViewById(R.id.updateBtn);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateVehicle();
            }
        });
    }

    private void setVehicleData(VehicleDTO vehicle) {
        model.setText(vehicle.getModel());
        plate.setText(vehicle.getLicenseNumber());
        seats.setText(vehicle.getPassengerSeats().toString());
        baby_transport.setChecked(vehicle.getBabyTransport());
        pet_transport.setChecked(vehicle.getPetTransport());
        if(vehicle.getVehicleType().equals(VehicleName.STANDARD)) {
            vehice_spinner.setSelection(0);
        }else if(vehicle.getVehicleType().equals(VehicleName.LUXURY)){
            vehice_spinner.setSelection(1);
        }else{
            vehice_spinner.setSelection(2);
        }


    }

    public void updateVehicle(){
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        VehicleDTO vehicleDTO = new VehicleDTO(id,
                this.model.getText().toString(), this.plate.getText().toString(),
                Integer.parseInt(this.seats.getText().toString()),
                this.pet_transport.isChecked(), this.baby_transport.isChecked());
        String selectedVehicle = vehice_spinner.getSelectedItem().toString();
        if(selectedVehicle.equals("STANDARD"))
            vehicleDTO.setVehicleType(VehicleName.STANDARD);
        else if(selectedVehicle.equals("LUXURY"))
            vehicleDTO.setVehicleType(VehicleName.LUXURY);
        else if(selectedVehicle.equals("VAN"))
            vehicleDTO.setVehicleType(VehicleName.VAN);

        Call<VehicleDTO> call = ServiceUtilis.userService.updateDriverVehicle(id, vehicleDTO);
        call.enqueue(new Callback<VehicleDTO>() {
            @Override
            public void onResponse(Call<VehicleDTO> call, Response<VehicleDTO> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VehicleActivity.this, "Update successfull!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VehicleDTO> call, Throwable t) {

            }
        });


    }

    public void getVehicle(){
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        Call<VehicleDTO> call = ServiceUtilis.userService.getDriverVehicle(id);
        call.enqueue(new Callback<VehicleDTO>() {
            @Override
            public void onResponse(Call<VehicleDTO> call, Response<VehicleDTO> response) {
                assert response.body() != null;
                setVehicleData(response.body());
            }

            @Override
            public void onFailure(Call<VehicleDTO> call, Throwable t) {

            }
        });
    }
}