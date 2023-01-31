package com.example.easygo.dto.ride;

import com.example.easygo.dto.PassengerDTOResult;
import com.example.easygo.dto.RouteDTO;
import com.example.easygo.model.Ride;
import com.example.easygo.model.enumerations.VehicleName;

import java.time.LocalDateTime;

public class RideDTORequest {
    private VehicleName vehicleType;
    private Boolean babyTransport;
    private Boolean petTransport;
    private PassengerDTOResult[] passengers;
    private RouteDTO[] locations;

    private LocalDateTime startTime;
    private double estimatedTime;
    private double kilometers;

    public RideDTORequest() {}

    public RideDTORequest(Boolean babyTransport, Boolean petTransport, PassengerDTOResult[] passengers, RouteDTO[] locations, VehicleName vehicleType, LocalDateTime startTime, double estimatedTime, double kilometers) {
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.passengers = passengers;
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.estimatedTime = estimatedTime;
        this.kilometers = kilometers;
    }

    public RideDTORequest(Ride ride) {
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();

        PassengerDTOResult[] passengers = new PassengerDTOResult[1];
        passengers[0] = new PassengerDTOResult(1, "Mirko", "Ivanic", "picture", "043242423", "mirko@gmail.com", "mirko123", true, false);
        this.passengers = new PassengerDTOResult[0];

        RouteDTO[] locations = new RouteDTO[1];
        locations[0] = new RouteDTO(ride.getLocations().get(0));
        this.locations = new RouteDTO[0];

        this.vehicleType = ride.getVehicleType();
        this.startTime = ride.getStartTime();
        this.estimatedTime = 15.0;
        this.kilometers = 1.2;
//        return new RideDTORequest(ride.isBabyproof(), ride.isPetsAllowed(), null, locations, ride.getVehicleName(), ride.getStartTime(), 15.0, 1.2);
    }

    public Boolean getBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(Boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public Boolean getPetTransport() {
        return petTransport;
    }

    public void setPetTransport(Boolean petTransport) {
        this.petTransport = petTransport;
    }

    public PassengerDTOResult[] getPassengers() {
        return passengers;
    }

    public void setPassengers(PassengerDTOResult[] passengers) {
        this.passengers = passengers;
    }

    public RouteDTO[] getLocations() {
        return locations;
    }

    public void setLocations(RouteDTO[] locations) {
        this.locations = locations;
    }

    public VehicleName getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleName vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }
}
