package com.example.easygo.mockup;

import com.example.easygo.model.Ride;
import com.example.easygo.model.Route;
import com.example.easygo.model.VehicleType;
import com.example.easygo.model.enumerations.RideStatus;
import com.example.easygo.model.users.Driver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MockupRides {

    public static HashMap<Integer, Ride> getRides() {
        HashMap<Integer, Ride> ridesMap = new HashMap<Integer, Ride>();

        LocalDateTime start1 = LocalDateTime.of(2022, 11, 15, 15, 15);
        LocalDateTime end1 = LocalDateTime.of(2022, 11, 15, 15, 45);
        Driver driver1 = MockupDrivers.getDrivers().get(1);

        LocalDateTime start2 = LocalDateTime.of(2022, 11, 15, 16, 15);
        LocalDateTime end2 = LocalDateTime.of(2022, 11, 15, 16, 45);
        Driver driver2 = MockupDrivers.getDrivers().get(2);

        LocalDateTime start3 = LocalDateTime.of(2022, 11, 15, 17, 15);
        LocalDateTime end3 = LocalDateTime.of(2022, 11, 15, 17, 45);
        Driver driver3 = MockupDrivers.getDrivers().get(3);

        RideStatus status = RideStatus.ACTIVE;

        Ride ride1 = new Ride(1, start1, end1, 20, 30, false, false, false, false, status, null, driver1, null, null);
        ArrayList<Route> routes1 = new ArrayList<Route>();
        routes1.add(new Route());
        routes1.get(0).getDeparture().setAddress("Augusta Cesarca 8, Novi Sad");
        routes1.get(0).getDestination().setAddress("Bulevar Cara Lazara 8, Novi Sad");
        ride1.setRoutes(routes1);

        Ride ride2 = new Ride(2, start2, end2, 10, 30, false, false, false, false, status, null, driver2, null, null);
        Ride ride3 = new Ride(3, start3, end3, 30, 30, false, false, false, false, status, null, driver3, null, null);

        ride1.getPassengers().add(MockupPassengers.getPassengers().get(1));
        ride2.getPassengers().add(MockupPassengers.getPassengers().get(2));
        ride2.getPassengers().add(MockupPassengers.getPassengers().get(3));
        ride3.getPassengers().add(MockupPassengers.getPassengers().get(1));

        ridesMap.put(ride1.getId(), ride1);
        ridesMap.put(ride2.getId(), ride2);
        ridesMap.put(ride3.getId(), ride3);

        return ridesMap;
    }

}
