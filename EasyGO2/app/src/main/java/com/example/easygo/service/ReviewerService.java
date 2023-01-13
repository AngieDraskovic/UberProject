package com.example.easygo.service;


import com.example.easygo.model.Vehicle;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface ReviewerService {
/*          SA VJEZBI KOD

    @POST(ServiceUtils.ADD)
    Call<ResponseBody> add(@Body TagToSend tag);
*/

    @GET("vehicle/all")
    Call<List<Vehicle>> getVehicles();


}
