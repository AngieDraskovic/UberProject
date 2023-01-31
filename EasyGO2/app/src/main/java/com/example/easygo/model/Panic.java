package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;
import com.example.easygo.model.users.User;
import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class Panic {
    private int id;
    private int[] time;
    private String reason;
    private User user;
    private Ride ride;

    public Panic() {}

    public Panic(Panic panic){
        this.id = panic.id;
        this.time = panic.time;
        this.reason = panic.reason;
        this.user = panic.user;
        this.ride = panic.ride;
    }

    public Panic(int id, LocalDateTime time, String reason, User user, Ride ride) {
        this.id = id;
        this.time = Convert.toIntArray(time);
        this.reason = reason;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }


}
