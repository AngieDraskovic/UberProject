package com.example.easygo.service;

import com.example.easygo.model.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IVehicleClass {

    @GET(ServiceUtilis.vehicle + "/all")
    Call<List<Vehicle>> getVehicles();
}
