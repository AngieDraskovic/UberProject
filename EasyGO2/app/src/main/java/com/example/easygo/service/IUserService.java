package com.example.easygo.service;

import com.example.easygo.dto.LoginDTO;
import com.example.easygo.dto.LoginResponseDTO;
import com.example.easygo.dto.PostPassengerDTO;
import com.example.easygo.dto.ResponseMessageDTO;
import com.example.easygo.dto.UpdateDriverDTO;
import com.example.easygo.dto.UpdatePassengerDTO;
import com.example.easygo.dto.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserService {

    @POST("login/")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @GET(ServiceUtilis.passenger + "/{id}")
    Call<UserDTO> getPassenger(@Path("id") Integer id);

    @GET(ServiceUtilis.driver + "/{id}")
    Call<UserDTO> getDriver(@Path("id") Integer id);


    @GET(ServiceUtilis.passenger + "/activate/{activationId}")
    Call<ResponseMessageDTO> activatePassenger(@Path("activationId") Integer id);

    @POST(ServiceUtilis.passenger)
    Call<PostPassengerDTO> createPassenger(@Body PostPassengerDTO passengerDTO);

    @PUT(ServiceUtilis.passenger + "/{id}")
    Call<UpdatePassengerDTO> updatePassenger(@Body UpdatePassengerDTO updatePassengerDTO,
                                             @Path("id") Integer id);

    @PUT(ServiceUtilis.driver + "/{id}")
    Call<UpdateDriverDTO> updateDriver(@Body UpdateDriverDTO updateDriverDTO,
                                       @Path("id") Integer id);
}
