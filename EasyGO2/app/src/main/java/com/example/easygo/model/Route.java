package com.example.easygo.model;

import com.example.easygo.dto.RouteDTO;
import com.example.easygo.model.users.Passenger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private int id;
    private Location startLocation;
    private Location endLocation;
    private double kilometers;
    private int estimatedTime;
    private Ride ride;

    public Route(RouteDTO routeDTO){
        this.startLocation = new Location(routeDTO.getDeparture());
        this.endLocation = new Location(routeDTO.getDestination());
    }
    public Route(int id, LocalDateTime startTime, LocalDateTime endTime, Location startLocation, Location endLocation, double kilometers, int estimatedTime, Ride ride) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.kilometers = kilometers;
        this.estimatedTime = estimatedTime;
        this.ride = ride;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
