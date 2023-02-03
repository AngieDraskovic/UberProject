package com.example.easygo.model;

import java.io.Serializable;

public class Location implements Serializable {
    private int id;
    private String address;
    private double latitude;
    private double longitude;



    public Location() {}

    public Location(Location location) {
        this.id = location.id;
        this.address = location.address;
        this.latitude = location.latitude;
        this.longitude = location.longitude;
    }

    public Location(int id, double longitude, double latitude, String address) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
