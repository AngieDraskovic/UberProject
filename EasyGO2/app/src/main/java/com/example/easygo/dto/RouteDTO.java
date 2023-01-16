package com.example.easygo.dto;

import com.example.easygo.model.Route;

public class RouteDTO {
    private LocationDTO departure;
    private LocationDTO destination;

    public LocationDTO getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDTO departure) {
        this.departure = departure;
    }

    public LocationDTO getDestination() {
        return destination;
    }

    public void setDestination(LocationDTO destination) {
        this.destination = destination;
    }

    public RouteDTO(Route route) {
        this.departure = new LocationDTO(route.getDeparture());
        this.destination =  new LocationDTO(route.getDestination());
    }
}
