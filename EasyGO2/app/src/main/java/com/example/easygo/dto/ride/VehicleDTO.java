package com.example.easygo.dto.ride;

import com.example.easygo.dto.LocationDTO;
import com.example.easygo.model.enumerations.VehicleName;

public class VehicleDTO {
    private Integer id;
    private Integer driverId;
    private VehicleName vehicleType;
    private String model;
    private String licenseNumber;
    private LocationDTO currentLocation;
    private Integer passengerSeats;
    private Boolean babyTransport;
    private Boolean petTransport;


    public VehicleDTO(Integer driverId, String model, String licenseNumber, Integer passengerSeats, Boolean babyTransport, Boolean petTransport) {
        this.driverId = driverId;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public void setVehicleType(VehicleName vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public VehicleName getVehicleType() {
        return vehicleType;
    }

    public String getModel() {
        return model;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public LocationDTO getCurrentLocation() {
        return currentLocation;
    }

    public Integer getPassengerSeats() {
        return passengerSeats;
    }

    public Boolean getBabyTransport() {
        return babyTransport;
    }

    public Boolean getPetTransport() {
        return petTransport;
    }
}
