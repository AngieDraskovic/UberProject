package com.example.easygo.model.users;

import com.example.easygo.model.Message;
import com.example.easygo.model.Rejection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String name;
    private String surname;
    private int profilePic;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;
    private String password;
    private boolean active;
    private boolean blocked;
    private List<Message> sentMessages;
    private List<Message> deliveredMessages;
    private List<Rejection> rejections;

    public User() {
        this.sentMessages = new ArrayList<Message>();
        this.deliveredMessages = new ArrayList<Message>();
        this.rejections = new ArrayList<Rejection>();
    }

    public User(User user){
        this();
        this.id = user.id;
        this.name = user.name;
        this.surname = user.surname;
        this.profilePic = user.profilePic;
        this.profilePicture = user.profilePicture;
        this.telephoneNumber = user.telephoneNumber;
        this.email = user.email;
        this.address = user.address;
        this.password = user.password;
        this.active = user.active;
        this.blocked = user.blocked;
    }

    public User(int id, String name, String surname, int profilePic, String phone, String email, String address, String password, boolean active, boolean blocked){
        this();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePic = profilePic;
        this.telephoneNumber = phone;
        this.email = email;
        this.address = address;
        this.password = password;
        this.blocked = blocked;
        this.active = active;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getDeliveredMessages() { return deliveredMessages; }
    public void setSentMessages(List<Message> sentMessages) { this.sentMessages = sentMessages; }
    public void setDeliveredMessages(List<Message> deliveredMessages) { this.deliveredMessages = deliveredMessages; }

    public List<Rejection> getRejections() {
        return rejections;
    }

    public void setRejections(List<Rejection> rejections) {
        this.rejections = rejections;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
