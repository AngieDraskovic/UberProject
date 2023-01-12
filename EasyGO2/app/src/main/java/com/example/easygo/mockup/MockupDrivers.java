package com.example.easygo.mockup;

import com.example.easygo.model.Vehicle;
import com.example.easygo.model.enumerations.VehicleName;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.easygo.R;

public class MockupDrivers {

    private static HashMap<Integer, Driver> driversMap = getDrivers();

    public static HashMap<Integer, Driver> getDrivers() {
        if (driversMap != null)
            return driversMap;

        driversMap = new HashMap<Integer, Driver>();

        Vehicle v1 = new Vehicle(1, "Honda", "423-FSDF-3D", 5, true, true, null, VehicleName.STANDARD, null, null);
        Vehicle v2 = new Vehicle(2, "Ferrari", "423-FSDF-3D", 5, true, true, null, VehicleName.VAN, null, null);
        Vehicle v3 = new Vehicle(3, "Audi", "423-FSDF-3D", 5, true, true, null, VehicleName.LUXURY, null, null);

        Driver driver1 = new Driver(1, "Dejan", "Stankovic", R.drawable.dejan_stankovic, "061-032-3230", "dejanstankovic@gmail.com", "Svetog Save 1", "dejan123", false, true, "1MM23", "FDS-33D-1", v1);
        Driver driver2 = new Driver(2, "Milos", "Milojevic", R.drawable.milos_milojevic, "062-032-3230", "milosmilojevic@gmail.com", "Svetog Save 2", "milos123", false, true, "2MM23", "FDS-33D-2", v2);
        Driver driver3 = new Driver(3, "Vladan", "Milojevic", R.drawable.vladan_milojevic, "063-032-3230", "vladanmilojevic@gmail.com", "Svetog Save 3", "vladan123", false, true, "3MM23", "FDS-33D-3", v3);

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
