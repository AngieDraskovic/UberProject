package com.example.easygo.mockup;

import com.example.easygo.model.users.Driver;

import java.util.ArrayList;
import java.util.HashMap;

public class MockupDrivers {

    private static HashMap<Integer, Driver> driversMap;

    public MockupDrivers(){
        createDrivers();
    }

    public void createDrivers() {
        Driver driver1 = new Driver(1, "Marko", "Markovic", -1, "061-032-3230", "markomarkovic@gmail.com", "Svetog Save 1", "marko123", false, false, "1MM23", "FDS-33D-1", null);
        Driver driver2 = new Driver(2, "Petar", "Petrovic", -1, "062-032-3230", "petarpetrovic@gmail.com", "Svetog Save 2", "petar123", false, false, "2MM23", "FDS-33D-2", null);
        Driver driver3 = new Driver(3, "Pera", "Peric", -1, "063-032-3230", "peraperic@gmail.com", "Svetog Save 3", "pera123", false, false, "3MM23", "FDS-33D-3", null);

        driversMap.put(driver1.getId(), driver1);
        driversMap.put(driver2.getId(), driver2);
        driversMap.put(driver3.getId(), driver3);
    }

    public static HashMap<Integer, Driver> getDrivers() {
        return driversMap;
    }


}
