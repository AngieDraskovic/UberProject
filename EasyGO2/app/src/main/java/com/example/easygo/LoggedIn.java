package com.example.easygo;

import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.model.users.User;

public class LoggedIn {
    private static Passenger passenger;
    private static Driver driver;
    private static User user;

    public static Passenger getPassenger() {
        return passenger;
    }

    public static void setPassenger(Passenger currPassenger) {
        passenger = currPassenger;
        user = currPassenger;
    }

    public static Driver getDriver() {
        return driver;
    }

    public static void setDriver(Driver currDriver) {
        driver = currDriver;
        user = currDriver;
    }

    public static User getUser() {
        return user;
    }
}
