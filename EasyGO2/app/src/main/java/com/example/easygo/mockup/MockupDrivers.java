package com.example.easygo.mockup;

import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;

import java.util.ArrayList;
import java.util.HashMap;

public class MockupDrivers {

    private static HashMap<Integer, Driver> driversMap = getDrivers();

    public static HashMap<Integer, Driver> getDrivers() {
        if (driversMap != null)
            return driversMap;

        driversMap = new HashMap<Integer, Driver>();

        Driver driver1 = new Driver(1, "Dejan", "Stankovic", -1, "061-032-3230", "dejanstankovic@gmail.com", "Svetog Save 1", "dejan123", false, false, "1MM23", "FDS-33D-1", null);
        Driver driver2 = new Driver(2, "Milos", "Milojevic", -1, "062-032-3230", "milosmilojevic@gmail.com", "Svetog Save 2", "milos123", false, false, "2MM23", "FDS-33D-2", null);
        Driver driver3 = new Driver(3, "Vladan", "Milojevic", -1, "063-032-3230", "vladanmilojevic@gmail.com", "Svetog Save 3", "vladan123", false, false, "3MM23", "FDS-33D-3", null);

        driversMap.put(driver1.getId(), driver1);
        driversMap.put(driver2.getId(), driver2);
        driversMap.put(driver3.getId(), driver3);

        return driversMap;
    }

    public static Driver findDriver(String email, String password) {
        for (Driver driver : getDrivers().values())
            if (driver.getEmail().equals(email) && driver.getPassword().equals(password))
                return driver;

        return null;
    }
}
