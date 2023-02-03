package com.example.easygo.service;
import com.example.easygo.BuildConfig;
import com.example.easygo.model.Vehicle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ServiceUtilis {


    public static final String EASYGO_SERVICE_API_PATH = BuildConfig.LOCALHOST;
    public static final String driver = "driver";
    public static final String vehicle = "vehicle";
    public static final String passenger = "passenger";
    public static final String user = "user";
    public static final String ride = "ride";
    public static final String review = "review";
    public static final String message = "message";


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
                .addInterceptor(new CustomInterceptor()).build();

        return client;
    }

    // definisanje konkretne instance servisa na internetu sa kojom vrsimo komunikaciju
    public static ReviewerService reviewerService = retrofit.create(ReviewerService.class);

    public static IUserService userService = retrofit.create(IUserService.class);
    public static IRideService rideService = retrofit.create(IRideService.class);
    public static IReviewService reviewService = retrofit.create(IReviewService.class);
    public static IDriverService driverService = retrofit.create(IDriverService.class);
    public static IMessageService messageService = retrofit.create(IMessageService.class);
}
