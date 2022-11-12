package com.example.easygo.mockup;

import com.example.easygo.model.users.Passenger;

import java.util.HashMap;

public class MockupPassengers {

    public static HashMap<Integer, Passenger> getPassengers() {
        HashMap<Integer, Passenger> passengersMap = new HashMap<Integer, Passenger>();

        Passenger passenger1 = new Passenger(4, "Mirko", "Ivanic", -1, "061-032-3230", "mirkoivanic@gmail.com", "Svetog Save 1", "mirko123", false);
        Passenger passenger2 = new Passenger(5, "Sale", "Katai", -1, "062-032-3230", "salekatai@gmail.com", "Svetog Save 2", "sale123", false);
        Passenger passenger3 = new Passenger(6, "Osman", "Bukari", -1, "063-032-3230", "osmanbukari@gmail.com", "Svetog Save 3", "osman123", false);

        passengersMap.put(passenger1.getId(), passenger1);
        passengersMap.put(passenger2.getId(), passenger2);
        passengersMap.put(passenger3.getId(), passenger3);

        return passengersMap;
    }

    public static Passenger findPassenger(String email, String password) {
        for (Passenger passenger : getPassengers().values())
            if (passenger.getEmail().equals(email) && passenger.getPassword().equals(password))
                return passenger;

        return null;
    }


}
