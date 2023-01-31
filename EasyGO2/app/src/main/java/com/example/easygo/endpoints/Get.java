package com.example.easygo.endpoints;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.WorkingHours;
import com.example.easygo.model.users.Driver;
import com.example.easygo.service.ServiceUtilis;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Get {

//    public void getActiveWorkingHour(){
//        WorkingHours workingHours = new WorkingHours();
//        workingHours.setStart(LocalDateTime.now());
//        workingHours.setEnd(LocalDateTime.now());
//
//        Call<WorkingHours> call = ServiceUtilis.rideService.createWorkingHour(workingHours);
//        call.enqueue(new Callback<WorkingHours>() {
//            @Override
//            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(),"Get active working-hour", Toast.LENGTH_SHORT).show();
//                    assert response.body() != null;
//                    WorkingHours workingHours = new WorkingHours(response.body());
//                    print("Izvrsio");
////                    LocationDTO locationDTO1 = new LocationDTO(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<WorkingHours> call, Throwable t) {
//                Toast.makeText(getContext(),"Failovao working-hour", Toast.LENGTH_SHORT).show();
//                print(t.toString());
//            }
//        });
//    }
}
