package com.example.easygo.dto;

import com.example.easygo.model.Location;

public class LocationDTO {
    private String address;
    private Double latitude;
    private Double longitude;

    public LocationDTO(Location location) {
        this.address = location.getAddress();
        this.latitude = location.getGeoLength();
        this.longitude = location.getGeoWidth();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
