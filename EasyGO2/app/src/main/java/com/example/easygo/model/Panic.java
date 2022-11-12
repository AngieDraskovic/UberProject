package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

import java.time.LocalDateTime;

public class Panic {
    private int id;
    private LocalDateTime time;
    private String reason;
    private Passenger passenger;
    private Ride ride;

    public Panic() {};

    public Panic(int id, LocalDateTime time, String reason, Passenger passenger, Ride ride) {
        this.id = id;
        this.time = time;
        this.reason = reason;
        this.passenger = passenger;
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

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}
