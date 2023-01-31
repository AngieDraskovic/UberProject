package com.example.easygo.dto.ride;

import com.example.easygo.dto.ride.OneRideOfPassengerDTO;

public class RidesOfPassengerDTO {
    private int totalCount;
    private OneRideOfPassengerDTO[] results;

    public RidesOfPassengerDTO(OneRideOfPassengerDTO[] results, int totalCount){
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public OneRideOfPassengerDTO[] getResults() {
        return results;
    }

    public void setResults(OneRideOfPassengerDTO[] results) {
        this.results = results;
    }
}
