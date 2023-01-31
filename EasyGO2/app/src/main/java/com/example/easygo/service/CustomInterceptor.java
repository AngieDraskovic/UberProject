package com.example.easygo.service;

import com.example.easygo.dto.TokenDTO;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException{

        TokenDTO tokenDTO = TokenDTO.getInstance();
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer" + tokenDTO.getToken())
                .addHeader("refreshToken", tokenDTO.getToken())
                .build();

        Response response = chain.proceed(request);
        return response;
    }
}

