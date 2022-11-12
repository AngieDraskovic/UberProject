package com.example.easygo.model;

import com.example.easygo.model.users.Passenger;

public class Review {
    private int id;
    private double grade;
    private String comment;
    private Ride ride;
    private Passenger reviewer;

    public Review() {};

    public Review(int id, double grade, String comment, Ride ride, Passenger reviewer) {
        this.id = id;
        this.grade = grade;
        this.comment = comment;
        this.ride = ride;
        this.reviewer = reviewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
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

    public Passenger getReviewer() {
        return reviewer;
    }

    public void setReviewer(Passenger reviewer) {
        this.reviewer = reviewer;
    }


}
