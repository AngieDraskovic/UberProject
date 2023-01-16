package com.example.easygo.dto;

import com.example.easygo.model.users.Driver;

public class DriverRideDTO {
    private Integer id;
    private String email;


    public DriverRideDTO(Driver d){
        this.id = d.getId();
        this.email = d.getEmail();
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
