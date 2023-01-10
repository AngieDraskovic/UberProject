package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

public class FavouriteRoute {
    private Passenger passenger;
    private Location startLocation;
    private Location endLocation;

    public FavouriteRoute() {};

    public FavouriteRoute(Passenger passenger, Location startLocation, Location endLocation) {
        this.passenger = passenger;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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
}
