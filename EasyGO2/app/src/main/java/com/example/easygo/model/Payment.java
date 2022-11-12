package com.example.easygo.model;

import com.example.easygo.model.enumerations.PaymentType;
import com.example.easygo.model.users.Passenger;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private double amount;
    private LocalDateTime dateTime;
    private Passenger passenger;
    private PaymentType type;

    public Payment() {}

    public Payment(int id, double amount, LocalDateTime dateTime, Passenger passenger, PaymentType type) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.passenger = passenger;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }
}
