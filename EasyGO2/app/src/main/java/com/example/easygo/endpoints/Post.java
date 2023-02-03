package com.example.easygo.endpoints;

import android.widget.Toast;

import com.example.easygo.model.WorkingHours;
import com.example.easygo.service.ServiceUtilis;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post {
//
//    public void createWorkingHour(){
//        WorkingHours workingHours = new WorkingHours();
////        workingHourDTOResponse.setId(92);
//        workingHours.setStart(LocalDateTime.now());
//        workingHours.setEnd(LocalDateTime.now());
//
//        Call<WorkingHours> call = ServiceUtilis.rideService.createWorkingHour(workingHours);
//        call.enqueue(new Callback<WorkingHours>() {
//            @Override
//            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(),"Create working-hour", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<WorkingHours> call, Throwable t) {
//                Toast.makeText(getContext(),"Failovao working-hour", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
