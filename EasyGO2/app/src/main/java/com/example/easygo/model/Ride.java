package com.example.easygo.model;

import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.utility.Convert;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ride implements Serializable {
    private int id;
    private int[] startTime;
    private int[] endTime;
    private double totalCost;
    private double estimatedTime;
    private double kilometers;
    private RideStatus status;
    private boolean babyTransport;
    private boolean petTransport;
    private VehicleName vehicleType;
    private List<Passenger> passengers;
    private Driver driver;
    private List<Route> locations;
    private List<Review> reviews;
    private Rejection rejection;
    private Panic panic;
    private String departure;
    private String destination;

    private List<Message> messages;

    public Ride(Ride ride){
        this.id = ride.id;
        this.startTime = ride.startTime;
        this.endTime = ride.endTime;
        this.totalCost = ride.totalCost;
        this.estimatedTime = ride.estimatedTime;
        this.kilometers = ride.kilometers;
        this.status = ride.status;
        this.babyTransport = ride.babyTransport;
        this.petTransport = ride.petTransport;
        this.vehicleType = ride.vehicleType;
        this.passengers = ride.passengers;
        this.driver = ride.driver;
        this.locations = ride.locations;
        this.reviews = ride.reviews;
        this.rejection = ride.rejection;
        this.panic = ride.panic;
    }

    public Ride(){
        this.messages = new ArrayList<Message>();
        this.passengers = new ArrayList<Passenger>();
        this.reviews = new ArrayList<Review>();
        this.locations = new ArrayList<Route>();
    }

    public Ride(int id, LocalDateTime startTime, LocalDateTime endTime, double price, int estimatedTime, boolean babyproof, boolean petsAllowed, VehicleName vehicleType, RideStatus status, Driver driver, Rejection rejection, Panic panic) {
        this();
        this.id = id;
        this.startTime = Convert.toIntArray(startTime);
        this.endTime = Convert.toIntArray(endTime);
        this.totalCost = price;
        this.estimatedTime = estimatedTime;
        this.babyTransport = babyproof;
        this.petTransport = petsAllowed;
        this.vehicleType = vehicleType;
        this.status = status;
        this.driver = driver;
        this.rejection = rejection;
        this.panic = panic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {return Convert.toLocalDateTime(startTime);}

    public void setStartTime(LocalDateTime startTime) {this.startTime = Convert.toIntArray(startTime);}

    public LocalDateTime getEndTime() {
        return Convert.toLocalDateTime(endTime);
    }

    public void setEndTime(LocalDateTime endTime) {this.startTime = Convert.toIntArray(endTime);}

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getEstimatedTime() {
        return 15;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rejection getRejection() {
        return rejection;
    }

    public void setRejection(Rejection rejection) {
        this.rejection = rejection;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Panic getPanic() {
        return panic;
    }

    public void setPanic(Panic panic) {
        this.panic = panic;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Route> getLocations() {
        return this.locations;
    }

    public void setRoute(List<Route> routes) {
        this.locations = routes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setLocations(List<Route> locations) {
        this.locations = locations;
    }

    public void setEstimatedTimeInMinutes(double estimatedTimeInMinutes) {this.estimatedTime = estimatedTimeInMinutes;}

    public double getKilometers() {return kilometers;}

    public void setKilometers(double kilometers) {this.kilometers = kilometers;}

    public String getBabyProofString() {
        return (this.babyTransport) ? "Yes" : "No";
    }

    public String getPetsAllowedString() {
        return (this.petTransport) ? "Yes" : "No";
    }

    public VehicleName getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleName vehicleType) {
        this.vehicleType = vehicleType;
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
}
