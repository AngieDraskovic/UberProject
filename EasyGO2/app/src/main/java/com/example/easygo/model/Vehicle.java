package com.example.easygo.model;

import com.example.easygo.model.users.Driver;

public class Vehicle {
    private int id;
    private String model;
    private String regPlates;
    private int seatNum;
    private boolean babyproof;
    private boolean petsAllowed;
    private VehicleType type;
    private Driver driver;
    private Location currLocation;

    public Vehicle() {}

    public Vehicle(int id, String model, String regPlates, int seatNum, boolean babyproof, boolean petsAllowed, VehicleType type, Driver driver, Location currLocation) {
        this.id = id;
        this.model = model;
        this.regPlates = regPlates;
        this.seatNum = seatNum;
        this.babyproof = babyproof;
        this.petsAllowed = petsAllowed;
        this.type = type;
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
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public boolean isBabyproof() {
        return babyproof;
    }

    public void setBabyproof(boolean babyproof) {
        this.babyproof = babyproof;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
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
}
