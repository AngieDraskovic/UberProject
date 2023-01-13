package com.example.easygo.service;

import android.app.Service;

import com.example.easygo.model.Vehicle;

import java.util.List;

import okhttp3.Call;
import retrofit2.Callback;

public class SyncService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Call<List<Vehicle>> call = reviewerService.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>()){
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response){
                if (response.isSuccessful()){
                    List<Vehicle> vehicles = response.body();
                }else{
                    // handle error reponse
                }
            }
        }
    }
}
