package com.example.easygo.service;

import com.example.easygo.dto.LoginDTO;
import com.example.easygo.dto.LoginResponseDTO;
import com.example.easygo.dto.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUserService {

    @POST("login/")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @GET(ServiceUtilis.passenger + "/{id}")
    Call<UserDTO> getPassenger(@Path("id") Integer id);


    @GET(ServiceUtilis.driver + "/{id}")
    Call<UserDTO> getDriver(@Path("id") Integer id);
}
