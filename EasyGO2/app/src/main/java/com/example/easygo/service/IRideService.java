package com.example.easygo.service;



import com.example.easygo.dto.RideDTORequest;
import com.example.easygo.dto.RideDTOResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRideService {

    @GET(ServiceUtilis.ride + "/driver/{driverId}/active")
    Call<RideDTOResponse> getDriverActiveRide(@Path("driverId") Integer id);

    @POST(ServiceUtilis.ride + "/create-example")
    Call<RideDTORequest> createExampleRide(@Body RideDTORequest rideDTO);

}
