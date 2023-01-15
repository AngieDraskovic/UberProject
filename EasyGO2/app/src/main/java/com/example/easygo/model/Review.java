package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

public class Review {
    private int id;
    private int grade;
    private String comment;
    private Ride ride;
    private Passenger passenger;

    public Review() {};

    public Review(int id, int grade, String comment, Ride ride, Passenger reviewer) {
        this.id = id;
        this.grade = grade;
        this.comment = comment;
        this.ride = ride;
        this.passenger = reviewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

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
