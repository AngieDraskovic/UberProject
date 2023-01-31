package com.example.easygo.model;

import com.example.easygo.model.users.User;
import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class Rejection {
    private int id;
    private Ride ride;
    private String reason;
    private User user;
    private int[] timeOfRejection;


    public Rejection() {}

    public Rejection(String reason) {
        this.reason = reason;
        this.timeOfRejection = Convert.toIntArray(LocalDateTime.now());
    }

    public Rejection(Rejection rejection) {
        this.id = rejection.id;
        this.ride = rejection.ride;
        this.reason = rejection.reason;
        this.user = rejection.user;
        this.timeOfRejection = rejection.timeOfRejection;
    }

    public Rejection(int id, LocalDateTime time, String reason, User user, Ride ride) {
        this.id = id;
        this.timeOfRejection = Convert.toIntArray(time);
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

    public LocalDateTime getTimeOfRejection() {return Convert.toLocalDateTime(timeOfRejection);}

    public void setTimeOfRejection(LocalDateTime timeOfRejection) {this.timeOfRejection = Convert.toIntArray(timeOfRejection);}

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
