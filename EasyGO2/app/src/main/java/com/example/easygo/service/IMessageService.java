package com.example.easygo.service;

import com.example.easygo.model.Message;
import com.example.easygo.model.Ride;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IMessageService {

    @GET(ServiceUtilis.message +"/{rideId}")
    Call<List<Message>> getRideMessages(@Path("rideId") Integer id);

    @GET(ServiceUtilis.message +"/all")
    Call<List<Message>> getAllMessages();

    @POST(ServiceUtilis.message)
    Call<Message> createMessage(@Body Message message);

}
