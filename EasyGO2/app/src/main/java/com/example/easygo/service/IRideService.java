package com.example.easygo.service;



import com.example.easygo.dto.LocationDTO;
import com.example.easygo.dto.WorkingHourDTOResponse;
import com.example.easygo.dto.ride.OneRideOfPassengerDTO;
import com.example.easygo.dto.ride.RideDTORequest;
import com.example.easygo.dto.ride.RideDTOResponse;
import com.example.easygo.model.Panic;
import com.example.easygo.model.Rejection;
import com.example.easygo.model.Ride;
import com.example.easygo.model.WorkingHours;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IRideService {

//    @GET(ServiceUtilis.ride + "/driver/{driverId}/active")
//    Call<RideDTOResponse> getDriverActiveRide(@Path("driverId") Integer id);

    @GET(ServiceUtilis.driver +"/{driverId}/next-rides")
    Call<List<OneRideOfPassengerDTO>> getDriverNextRides(@Path("driverId") Integer id);

    @GET(ServiceUtilis.driver +"/{driverId}/active-working-hour")
    Call<WorkingHourDTOResponse> getDriverActiveWorikingHour(@Path("driverId") Integer id);

    @POST(ServiceUtilis.ride + "/create-example")
    Call<RideDTORequest> createExampleRide(@Body RideDTORequest rideDTO);

    /* 1 - CREATE RIDE : api/ride */
    @POST(ServiceUtilis.ride)
    Call<Ride> createRide(@Body Ride ride);

    /* 2 - GET ALL RIDES : api/ride/all */
    @GET(ServiceUtilis.ride + "/all")
    Call<List<Ride>> getAllRides();

    /* 3 - ACCEPT RIDE : api/ride/{id}/accept */
    @PUT(ServiceUtilis.ride + "/{id}/accept")
    Call<Ride> acceptRide(@Path("id") Integer id);

    /* 4 - START RIDE : api/ride/{id}/start */
    @PUT(ServiceUtilis.ride + "/{id}/start")
    Call<Ride> startRide(@Path("id") Integer id);

    /* 5 - FINISH RIDE : api/ride/{id}/end */
    @PUT(ServiceUtilis.ride + "/{id}/end")
    Call<Ride> finishRide(@Path("id") Integer id);

    /* 6 - REJECT RIDE : api/ride/{id}/cancel */
    @PUT(ServiceUtilis.ride + "/{id}/cancel")
    Call<Ride> rejectRide(@Body Rejection rejection, @Path("id") Integer id);

    /* 7 - PANIC RIDE : api/ride/{id}/panic-ride */
    @PUT(ServiceUtilis.ride + "/{id}/panic-ride")
    Call<Ride> panicRide(@Body Panic panic, @Path("id") Integer id);

    @GET(ServiceUtilis.ride + "/passenger/{passengerId}/active")
    Call<Ride> getPassengerActiveRide(@Path("passengerId") Integer passengerId);

    @GET(ServiceUtilis.ride + "/driver/{driverId}/active")
    Call<Ride> getDriverActiveRide(@Path("driverId") Integer driverId);


    /* SAMO RADI TESTIRANJA POST I DATUMA */
    @POST(ServiceUtilis.ride + "/location")
    Call<LocationDTO> createLocation(@Body LocationDTO locationDTO);

    @POST(ServiceUtilis.ride + "/test-working-hours")
    Call<WorkingHours> createWorkingHour(@Body WorkingHours workingHours);

}
