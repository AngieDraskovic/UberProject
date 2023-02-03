package com.example.easygo.service;

import com.example.easygo.model.Ride;
import com.example.easygo.model.WorkingHours;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IDriverService {

    @POST(ServiceUtilis.driver + "/{driverId}/working-hour")
    Call<WorkingHours> createWorkingHour(@Body WorkingHours workingHours, @Path("driverId") Integer driverId);

    @GET(ServiceUtilis.driver + "/{driverId}/active-working-hour")
    Call<WorkingHours> getDriverActiveWorkingHour(@Path("driverId") Integer driverId);

    @PUT(ServiceUtilis.driver + "/working-hour/{working-hour-id}")
    Call<WorkingHours> updateWorkingHour(@Body WorkingHours workingHours, @Path("working-hour-id") Integer workingHourId);
}
