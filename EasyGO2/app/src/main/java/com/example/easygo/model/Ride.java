package com.example.easygo.model;

import com.example.easygo.dto.RideDTOResponse;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ride {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    private double estimatedTime;
    private boolean panicButton;
    private boolean babyproof;
    private boolean petsAllowed;
    private boolean splitFare;
    private RideStatus status;
    private VehicleType vehicleType;
    private Driver driver;
    private Rejection rejection;
    private List<Message> messages;
    private Panic panic;
    private List<Passenger> passengers;
    private List<Payment> payments;
    private Route route;
    private List<Review> reviews;

    public Ride(){
        this.messages = new ArrayList<Message>();
        this.passengers = new ArrayList<Passenger>();
        this.payments = new ArrayList<Payment>();
        this.reviews = new ArrayList<Review>();
    }

    public Ride(RideDTOResponse rideResponse){
        this.id = rideResponse.getId();
        this.babyproof = rideResponse.getBabyTransport();
        this.estimatedTime = rideResponse.getEstimatedTimeInMinutes();
        this.status = rideResponse.getStatus();
        this.panicButton = false;
        this.petsAllowed = rideResponse.getPetTransport();
        this.startTime = rideResponse.getStartTime();
        this.endTime = rideResponse.getEndTime();
        this.driver = new Driver(rideResponse.getDriver());
        if(rideResponse.getRejection() != null) {
            this.rejection = new Rejection(rideResponse.getRejection());
        }
        this.price = rideResponse.getTotalCost();
        this.route = new Route(rideResponse.getLocations()[0]);
        this.vehicleType = new VehicleType();
        this.vehicleType.setVehicleName(VehicleName.STANDARD);

    }

    public Ride(int id, LocalDateTime startTime, LocalDateTime endTime, double price, int estimatedTime, boolean panicButton, boolean babyproof, boolean petsAllowed, boolean splitFare, RideStatus status, VehicleType vehicleType, Driver driver, Rejection rejection, Panic panic, Route route) {
        this();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.estimatedTime = estimatedTime;
        this.panicButton = panicButton;
        this.babyproof = babyproof;
        this.petsAllowed = petsAllowed;
        this.splitFare = splitFare;
        this.status = status;
        this.vehicleType = vehicleType;
        this.driver = driver;
        this.rejection = rejection;
        this.panic = panic;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isPanicButton() {
        return panicButton;
    }

    public void setPanicButton(boolean panicButton) {
        this.panicButton = panicButton;
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

    public boolean isSplitFare() {
        return splitFare;
    }

    public void setSplitFare(boolean splitFare) {
        this.splitFare = splitFare;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
