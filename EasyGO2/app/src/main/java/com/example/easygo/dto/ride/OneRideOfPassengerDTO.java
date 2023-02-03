package com.example.easygo.dto.ride;

import com.example.easygo.dto.PassengerRideDTO;
import com.example.easygo.dto.RejectionDTO;
import com.example.easygo.dto.RouteDTO;
import com.example.easygo.dto.ride.DriverRideDTO;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Route;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Passenger;

import java.time.LocalDateTime;
import java.util.List;

public class OneRideOfPassengerDTO {
    private Integer id;
    private int[] startTime;
    private int[] endTime;
    private Double totalCost;
    private DriverRideDTO driver;
    private RideStatus status;
    private Double estimatedTimeInMinutes;
    private Boolean babyTransport;
    private Boolean petTransport;
    private VehicleName vehicleType;
    private PassengerRideDTO[] passengers;
    private RouteDTO[] locations;
    private RejectionDTO rejection;

    /*
    public OneRideOfPassengerDTO(Ride ride){
        this.id = ride.getId();
        this.startTime = ride.getStartTime();
        this.endTime = ride.getEndTime();
        this.totalCost = ride.getPrice();
        this.driver = new DriverRideDTO(ride.getDriver());
        List<Passenger> passengers = ride.getPassengers();
        PassengerRideDTO[] p = new PassengerRideDTO[passengers.size()];
        int iter = 0;
        for(Passenger passenger : passengers){
            p[iter] = new PassengerRideDTO(passenger);
            iter++;
        }
        this.passengers = p;
        this.estimatedTimeInMinutes = Double.valueOf(ride.getEstimatedTime());
        this.vehicleType = ride.getVehicleType().getVehicleName();
        this.babyTransport = ride.isBabyproof();
        this.petTransport = ride.isPetsAllowed();
        List<Route> routes = ride.getRoutes();
        RouteDTO[] locationDTOS = new RouteDTO[routes.size()];
        iter = 0;
        for(Route route : routes){
            locationDTOS[iter] = new RouteDTO(route);
            iter++;
        }
        this.locations = locationDTOS;
        this.rejection = new RejectionDTO(ride.getRejection());
    }
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
/*
    public LocalDateTime getStartTimeLocal() {
        return startTime;
    }

    public void setStartTimeLocal(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTimeLocal() {
        return endTime;
    }

    public void setEndTimeLocal(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    */

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public DriverRideDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverRideDTO driver) {
        this.driver = driver;
    }

    public Double getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Double estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
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

    public VehicleName getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleName vehicleType) {
        this.vehicleType = vehicleType;
    }

    public PassengerRideDTO[] getPassengers() {
        return passengers;
    }

    public void setPassengers(PassengerRideDTO[] passengers) {
        this.passengers = passengers;
    }

    public RouteDTO[] getLocations() {
        return locations;
    }

    public void setLocations(RouteDTO[] locations) {
        this.locations = locations;
    }

    public RejectionDTO getRejection() {
        return rejection;
    }

    public void setRejection(RejectionDTO rejection) {
        this.rejection = rejection;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
