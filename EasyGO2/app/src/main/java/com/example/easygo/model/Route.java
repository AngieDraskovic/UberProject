package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Location departure;
    private Location destination;
    private double kilometers;
    private int estimatedTime;
    private double price;
    private List<Passenger> passengers;

    public Route(){
        this.passengers = new ArrayList<Passenger>();
        this.departure = new Location();
        this.destination = new Location();
    }

    public Route(int id, LocalDateTime startTime, LocalDateTime endTime, Location startLocation, Location endLocation, double kilometers, int estimatedTime, double price) {
        this();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.departure = startLocation;
        this.destination = endLocation;
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

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
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
