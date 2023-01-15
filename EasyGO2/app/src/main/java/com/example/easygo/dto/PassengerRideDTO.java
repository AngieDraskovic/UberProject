package com.example.easygo.dto;

import com.example.easygo.model.users.Passenger;

public class PassengerRideDTO {

    private Integer id;
    private String email;


    public PassengerRideDTO(Passenger p){
        this.id = p.getId();
        this.email = p.getEmail();

    }
}
