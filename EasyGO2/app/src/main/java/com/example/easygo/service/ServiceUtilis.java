package com.example.easygo.service;
import com.example.easygo.model.Vehicle;

import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ServiceUtilis {


    public static final String EASYGO_SERVICE_API_PATH = "http://localhost:8080/api/";
    public static final String VEHICLE = "Vehicle";

    // definisemo retrofit instancu preko koje se odvija komunikacija
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(EASYGO_SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(test())
            .build();

    // ova metoda sluzi za debug da vidimo da li zahtjevi odlaze i stizu odgovori
    public static OkHttpClient test(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        return client;
    }

    // definisanje konkretne instance servisa na internetu sa kojom vrsimo komunikaciju
    public static ReviewerService reviewerService = retrofit.create(ReviewerService.class);

}
