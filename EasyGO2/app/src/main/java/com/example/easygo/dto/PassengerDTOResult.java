package com.example.easygo.dto;

import com.example.easygo.model.users.Passenger;

public class PassengerDTOResult {

    private Integer id;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;
    private Boolean active;
    private Boolean blocked;

    public PassengerDTOResult(Integer id, String name, String surname, String profilePicture, String telephoneNumber, String email, String address, Boolean active, Boolean blocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.active = active;
        this.blocked = blocked;
    }

    public PassengerDTOResult(Passenger passenger){
        this.id = passenger.getId();
        this.name = passenger.getName();
        this.surname = passenger.getSurname();
        this.email = passenger.getEmail();
        this.profilePicture = String.valueOf(passenger.getProfilePic());
        this.telephoneNumber = passenger.getTelephoneNumber();
        this.address = passenger.getAddress();
        this.active = passenger.isActive();
        this.blocked = passenger.isBlocked();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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
}
