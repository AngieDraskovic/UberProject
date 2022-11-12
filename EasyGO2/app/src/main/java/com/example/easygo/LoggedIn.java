package com.example.easygo;

import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;

public class LoggedIn {
    private static Passenger passenger;
    private static Driver driver;

    public static Passenger getPassenger() {
        return passenger;
    }

    public static void setPassenger(Passenger currPassenger) {
        passenger = currPassenger;
    }

    public static Driver getDriver() {
        return driver;
    }

    public static void setDriver(Driver currDriver) {
        driver = currDriver;
    }
}
