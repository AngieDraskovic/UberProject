package com.example.easygo.model;

import com.example.easygo.model.enumerations.VehicleName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VehicleType implements Serializable {
    private int id;
    private VehicleName vehicleName;
    private double pricePerKilometer;

    public VehicleType() {

    }
    public VehicleType(VehicleType vehicleType) {
        this.id = vehicleType.id;
        this.vehicleName = vehicleType.vehicleName;
        this.pricePerKilometer = vehicleType.pricePerKilometer;
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

    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(int pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }

}
