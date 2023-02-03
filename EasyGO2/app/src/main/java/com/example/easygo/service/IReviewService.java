package com.example.easygo.service;

import com.example.easygo.model.Review;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IReviewService {

    @POST(ServiceUtilis.review + "/{rideId}/{passengerId}")
    Call<Review> createReview(@Body Review review, @Path("rideId") Integer rideId, @Path("passengerId") Integer passengerId);
}
