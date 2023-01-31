package com.example.easygo.model;

import com.example.easygo.model.enumerations.MessaggeType;
import com.example.easygo.model.users.User;
import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private int[] time;
    private MessaggeType type;
    private Ride ride;

    public Message() {}

    public Message(Message message) {
        this.id = message.id;
        this.sender = message.sender;
        this.receiver = message.receiver;
        this.text = message.text;
        this.time = message.time;
        this.type = message.type;
        this.ride = message.ride;
    }

    public Message(int id, String text, LocalDateTime time, MessaggeType type, User sender, User deliverer, Ride ride) {
        this.id = id;
        this.text = text;
        this.time = Convert.toIntArray(time);
        this.type = type;
        this.sender = sender;
        this.receiver = deliverer;
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

    public LocalDateTime getTime() {return Convert.toLocalDateTime(time);}

    public void setTime(LocalDateTime time) {this.time = Convert.toIntArray(time);}

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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
                ", deliverer=" + receiver +
                ", ride=" + ride +
                '}';
    }
}
