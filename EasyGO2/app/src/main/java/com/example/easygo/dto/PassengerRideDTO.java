package com.example.easygo.dto;

import com.example.easygo.model.users.Passenger;

public class PassengerRideDTO {
    private Integer id;
    private String email;


    public PassengerRideDTO(Passenger p){
        this.id = p.getId();
        this.email = p.getEmail();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
