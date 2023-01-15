package com.example.easygo.service;

import com.example.easygo.dto.RideDTOResponse;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRideService {
    @GET(ServiceUtilis.passenger + "/{id}")
    Call<UserDTO> getPassenger(@Path("id") Integer id);

/*
        @POST("login/")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @POST("login/")
    Call<RideResponseDTO> createRide(@Body RideRequestDTO rideRequestDTO);
* */
    @GET(ServiceUtilis.ride + "/{id}")
    Call<RideDTOResponse> getRide(@Path("id") Integer id);

    @GET(ServiceUtilis.vehicle + "/all")
    Call<List<Vehicle>> getVehicles();


}
