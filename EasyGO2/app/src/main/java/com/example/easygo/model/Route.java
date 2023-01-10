package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Location startLocation;
    private Location endLocation;
    private double kilometers;
    private int estimatedTime;
    private double price;
    private List<Passenger> passengers;

    public Route(){
        this.passengers = new ArrayList<Passenger>();
    }

    public Route(int id, LocalDateTime startTime, LocalDateTime endTime, Location startLocation, Location endLocation, double kilometers, int estimatedTime, double price) {
        this();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.kilometers = kilometers;
        this.estimatedTime = estimatedTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
