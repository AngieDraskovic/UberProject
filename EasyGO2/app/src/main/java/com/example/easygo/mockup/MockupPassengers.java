package com.example.easygo.mockup;

import com.example.easygo.model.users.Passenger;

import java.util.HashMap;

public class MockupPassengers {
    private static HashMap<Integer, Passenger> passengersMap;

    public MockupPassengers(){
        createPassengers();
    }

    public void createPassengers() {
        Passenger driver1 = new Passenger(4, "Marko", "Markovic", -1, "061-032-3230", "markomarkovic@gmail.com", "Svetog Save 1", "marko123", false);
        Passenger driver2 = new Passenger(5, "Petar", "Petrovic", -1, "062-032-3230", "petarpetrovic@gmail.com", "Svetog Save 2", "petar123", false);
        Passenger driver3 = new Passenger(6, "Pera", "Peric", -1, "063-032-3230", "peraperic@gmail.com", "Svetog Save 3", "pera123", false);

        passengersMap.put(driver1.getId(), driver1);
        passengersMap.put(driver2.getId(), driver2);
        passengersMap.put(driver3.getId(), driver3);
    }

    public static HashMap<Integer, Passenger> getPassengers() {
        return passengersMap;
    }

}
