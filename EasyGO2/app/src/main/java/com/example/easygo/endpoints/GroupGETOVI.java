package com.example.easygo.endpoints;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.easygo.driver.DriverMainActivity;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.Ride;
import com.example.easygo.model.WorkingHours;
import com.example.easygo.model.users.Driver;
import com.example.easygo.service.ServiceUtilis;

import java.time.LocalDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupGETOVI {

    // 1 - GET ALL RIDES

//    public void getAllRides(){
//        WorkingHours workingHours = new WorkingHours();
//        workingHours.setStart(LocalDateTime.now());
//        workingHours.setEnd(LocalDateTime.now());
//
//        Call<List<Ride>> call = ServiceUtilis.rideService.getAllRides();
//        call.enqueue(new Callback<List<Ride>>() {
//            @Override
//            public void onResponse(Call<List<Ride>> call, Response<List<Ride>> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(),"Get all rides", Toast.LENGTH_SHORT).show();
//                    assert response.body() != null;
//                    List<Ride> rides = response.body();
//                    print("Izvrsio");
////                    LocationDTO locationDTO1 = new LocationDTO(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Ride>> call, Throwable t) {
//                Toast.makeText(getContext(),"Failovao working-hour", Toast.LENGTH_SHORT).show();
//                print(t.toString());
//            }
//        });
//    }
}
