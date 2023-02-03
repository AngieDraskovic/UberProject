package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;
import com.example.easygo.model.users.User;
import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class Panic {
    private int id;
    private int[] time;
    private String reason;
    private Integer userId;
    private Ride ride;

    public Panic() {}

    public Panic(String reason, Integer userId, Ride ride) {
        this.reason = reason;
        this.userId = userId;
        this.ride = ride;
        this.time = Convert.toIntArray(LocalDateTime.now());
    }

    public Panic(Panic panic){
        this.id = panic.id;
        this.time = panic.time;
        this.reason = panic.reason;
        this.userId = panic.userId;
        this.ride = panic.ride;
    }

    public Panic(int id, LocalDateTime time, String reason, Integer userId, Ride ride) {
        this.id = id;
        this.time = Convert.toIntArray(time);
        this.reason = reason;
        this.userId = userId;
        this.ride = ride;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {return Convert.toLocalDateTime(time);}

    public void setTime(LocalDateTime time) {this.time = Convert.toIntArray(time);}

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }


}
