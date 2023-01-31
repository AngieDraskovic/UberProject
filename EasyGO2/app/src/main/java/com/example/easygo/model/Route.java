package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {
    private int id;
    private Location departure;
    private Location destination;

    public Route(){
        this.departure = new Location();
        this.destination = new Location();
    }

    public Route(Route route) {
        this.id = route.id;
        this.departure = route.departure;
        this.destination = route.destination;
    }

    public Route(int id, Location startLocation, Location endLocation) {
        this();
        this.id = id;
        this.departure = startLocation;
        this.destination = endLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
