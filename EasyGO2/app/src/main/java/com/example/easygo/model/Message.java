package com.example.easygo.model;

import com.example.easygo.model.enumerations.MessaggeType;
import com.example.easygo.model.users.User;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String text;
    private LocalDateTime time;
    private MessaggeType type;
    private User sender;
    private User deliverer;
    private Ride ride;

    public Message() {};

    public Message(int id, String text, LocalDateTime time, MessaggeType type, User sender, User deliverer, Ride ride) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.type = type;
        this.sender = sender;
        this.deliverer = deliverer;
        this.ride = ride;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public MessaggeType getType() {
        return type;
    }

    public void setType(MessaggeType type) {
        this.type = type;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(User deliverer) {
        this.deliverer = deliverer;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", sender=" + sender +
                ", deliverer=" + deliverer +
                ", ride=" + ride +
                '}';
    }
}
