package com.example.easygo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.easygo.model.enumerations.MessaggeType;
import com.example.easygo.model.users.User;
import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class Message implements Parcelable {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private int[] time;
    private MessaggeType type;
    private int rideId;
    private Ride ride;

    public Message() {}

    public Message(String text, User sender, User deliverer, int rideId) {
        this.text = text;
        this.time = Convert.toIntArray(LocalDateTime.now());
        this.type = MessaggeType.RIDE;
        this.sender = sender;
        this.receiver = deliverer;
        this.rideId = rideId;
    }

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

    protected Message(Parcel in) {
        id = in.readInt();
        text = in.readString();
        time = in.createIntArray();
        rideId = in.readInt();
        sender = in.readParcelable(User.class.getClassLoader());
        receiver = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(text);
        parcel.writeIntArray(time);
        parcel.writeInt(rideId);
        parcel.writeParcelable(sender, i);
        parcel.writeParcelable(receiver, i);
    }
}
