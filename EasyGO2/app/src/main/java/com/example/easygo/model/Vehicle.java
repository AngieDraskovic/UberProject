package com.example.easygo.model;

import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Driver;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private int id;
    private String model;
    private VehicleName vehicleName;
    private String licenseNumber;
    private int passengerSeats;
    private boolean babyTransport;
    private boolean petTransport;
    private VehicleType vehicleType;

    private Driver driver;
    private Location currLocation;

    public Vehicle() {}

    public Vehicle(int id, String model, String regPlates, int seatNum, boolean babyproof, boolean petsAllowed, VehicleType type, VehicleName vehicleName, Driver driver, Location currLocation) {
        this.id = id;
        this.model = model;
        this.licenseNumber = regPlates;
        this.passengerSeats = seatNum;
        this.babyTransport = babyproof;
        this.petTransport = petsAllowed;
        this.vehicleType = type;
        this.vehicleName = vehicleName;
        this.driver = driver;
        this.currLocation = currLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getSeatNum() {
        return passengerSeats;
    }

    public void setSeatNum(int seatNum) {
        this.passengerSeats = seatNum;
    }

    public boolean isBabyproof() {
        return babyTransport;
    }

    public void setBabyproof(boolean babyproof) {
        this.babyTransport = babyproof;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(Location currLocation) {
        this.currLocation = currLocation;
    }

    public VehicleName getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(VehicleName vehicleName) {
        this.vehicleName = vehicleName;
    }
}
