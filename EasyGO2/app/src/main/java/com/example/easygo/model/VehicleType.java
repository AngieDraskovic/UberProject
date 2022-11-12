package com.example.easygo.model;

import com.example.easygo.model.enumerations.VehicleName;

import java.util.ArrayList;
import java.util.List;

public class VehicleType {
    private int id;
    private VehicleName vehicleName;
    private int pricePerKilometer;
    private List<Vehicle> vehicles;
    private List<Ride> rides;

    public VehicleType() {
        this.vehicles = new ArrayList<Vehicle>();
        this.rides = new ArrayList<Ride>();
    }

    public VehicleType(int id, VehicleName vehicleName, int pricePerKilometer) {
        this();
        this.id = id;
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleName getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(VehicleName vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(int pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }
}
