package com.example.easygo.model;

import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Driver;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private int id;
    private String model;
    private String regPlates;
    private int numSeats;
    private boolean babyProof;
    private boolean petsAllowed;
    private VehicleType vehicleType;
    private VehicleName vehicleName;
    private Driver driver;
    private Location currLocation;

    public Vehicle() {}

    public Vehicle(int id, String model, String regPlates, int seatNum, boolean babyproof, boolean petsAllowed, VehicleType type, VehicleName vehicleName, Driver driver, Location currLocation) {
        this.id = id;
        this.model = model;
        this.regPlates = regPlates;
        this.numSeats = seatNum;
        this.babyProof = babyproof;
        this.petsAllowed = petsAllowed;
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

    public String getRegPlates() {
        return regPlates;
    }

    public void setRegPlates(String regPlates) {
        this.regPlates = regPlates;
    }

    public int getSeatNum() {
        return numSeats;
    }

    public void setSeatNum(int seatNum) {
        this.numSeats = seatNum;
    }

    public boolean isBabyproof() {
        return babyProof;
    }

    public void setBabyproof(boolean babyproof) {
        this.babyProof = babyproof;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
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
