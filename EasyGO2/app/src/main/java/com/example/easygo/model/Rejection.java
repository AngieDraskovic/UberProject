package com.example.easygo.model;

import com.example.easygo.dto.RejectionDTO;
import com.example.easygo.model.users.User;

import java.time.LocalDateTime;

public class Rejection {
    private int id;
    private LocalDateTime time;
    private String reason;
    private User user;
    private Ride ride;

    public Rejection() {}

    public Rejection(RejectionDTO rejectionDTO){
        this.time = rejectionDTO.getTimeOfRejection();
        this.reason = rejectionDTO.getReason();
    }
    public Rejection(int id, LocalDateTime time, String reason, User user, Ride ride) {
        this.id = id;
        this.time = time;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

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
