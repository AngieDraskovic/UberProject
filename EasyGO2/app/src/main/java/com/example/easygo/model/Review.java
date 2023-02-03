package com.example.easygo.model;

import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;

public class Review {
    private int id;
    private int driverGrade;
    private int vehicleGrade;
    private String comment;
    private Passenger passenger;
    private Driver driver;
    private Vehicle vehicle;
    private Ride ride;


    public Review() {};

    public Review(int driverGrade, int vehicleGrade, String comment) {
        this.driverGrade = driverGrade;
        this.vehicleGrade = vehicleGrade;
        this.comment = comment;
    }

    public Review(Review review) {
        this.id = review.id;
        this.driverGrade = review.driverGrade;
        this.vehicleGrade = review.vehicleGrade;
        this.comment = review.comment;
        this.passenger = review.passenger;
        this.driver = review.driver;
        this.vehicle = review.vehicle;
        this.ride = review.ride;
    }

    public Review(int id, int driverGrade, int vehicleGrade, String comment, Passenger passenger, Driver driver, Vehicle vehicle, Ride ride) {
        this.id = id;
        this.driverGrade = driverGrade;
        this.vehicleGrade = vehicleGrade;
        this.comment = comment;
        this.passenger = passenger;
        this.driver = driver;
        this.vehicle = vehicle;
        this.ride = ride;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverGrade() {return driverGrade;}

    public void setDriverGrade(int driverGrade) {this.driverGrade = driverGrade;}

    public int getVehicleGrade() {return vehicleGrade;}

    public void setVehicleGrade(int vehicleGrade) {this.vehicleGrade = vehicleGrade;}

    public Driver getDriver() {return driver;}

    public void setDriver(Driver driver) {this.driver = driver;}

    public Vehicle getVehicle() {return vehicle;}

    public void setVehicle(Vehicle vehicle) {this.vehicle = vehicle;}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


}
