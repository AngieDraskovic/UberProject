package com.example.easygo.dto;

import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.enumerations.VehicleName;

import java.time.LocalDateTime;
import java.util.Arrays;

public class RideDTOResponse {
    private Integer id;
    private int[] startTime;
    private int[] endTime;
    private Double totalCost;
    private DriverRideDTO driver;
    private Double estimatedTimeInMinutes;
    private RideStatus status;
    private RejectionDTO rejection;
    private Boolean babyTransport;
    private Boolean petTransport;
    private VehicleName vehicleType;
    private PassengerRideDTO[] passengers;
    private RouteDTO[] locations;
    private String departure;
    private String destination;
    private ReviewDTO[] reviews;

    public RideDTOResponse(){}

    public RideDTOResponse(Integer id, LocalDateTime startTime, LocalDateTime endTime, Double totalCost, DriverRideDTO driver, Double estimatedTimeInMinutes, RideStatus status, RejectionDTO rejection, Boolean babyTransport, Boolean petTransport, VehicleName vehicleType, PassengerRideDTO[] passengers, RouteDTO[] locations, String departure, String destination, ReviewDTO[] reviews) {
        this.id = id;
//        this.startTime = startTime;
//        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.status = status;
        this.rejection = rejection;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.vehicleType = vehicleType;
        this.passengers = passengers;
        this.locations = locations;
        this.departure = departure;
        this.destination = destination;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }

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

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public RejectionDTO getRejection() {
        return rejection;
    }

    public void setRejection(RejectionDTO rejection) {
        this.rejection = rejection;
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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ReviewDTO[] getReviews() {
        return reviews;
    }

    public void setReviews(ReviewDTO[] reviews) {
        this.reviews = reviews;
    }


    @Override
    public String toString() {
        return "RideDTOResponse{" +
                "id=" + id +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
                ", totalCost=" + totalCost +
//                ", driver=" + driver +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", status=" + status +
//                ", rejection=" + rejection +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
//                ", vehicleType=" + vehicleType +
//                ", passengers=" + Arrays.toString(passengers) +
//                ", locations=" + Arrays.toString(locations) +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
//                ", reviews=" + Arrays.toString(reviews) +
                '}';
    }
}
