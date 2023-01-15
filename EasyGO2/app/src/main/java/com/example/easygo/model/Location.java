package com.example.easygo.model;

import com.example.easygo.dto.LocationDTO;

public class Location {
    private int id;
    private double geoWidth;
    private double geoLength;
    private String address;

    public Location() {};

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Location(LocationDTO locationDTO){
        this.address = locationDTO.getAddress();
        this.geoLength = locationDTO.getLatitude();
        this.geoWidth = locationDTO.getLongitude();
    }
    public Location(int id, double lenght, double width, String address) {
        this.id = id;
        this.geoWidth = lenght;
        this.geoLength = width;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGeoWidth() {
        return geoWidth;
    }

    public void setGeoWidth(double geoWidth) {
        this.geoWidth = geoWidth;
    }

    public double getGeoLength() {
        return geoLength;
    }

    public void setGeoLength(double geoLength) {
        this.geoLength = geoLength;
    }
}
