package com.example.easygo.model.users;

import com.example.easygo.R;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.FavouriteRoute;
import com.example.easygo.model.Panic;
import com.example.easygo.model.Payment;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Route;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {

    private List<Ride> rides;
    private List<Panic> panics;
    private List<Payment> payments;
    private List<Route> routes;
    private List<FavouriteRoute> favouriteRoutes;

    public Passenger() {};

    public Passenger(User user) {
        super(user);
    }

    public Passenger(UserDTO userDTO){
        super(userDTO.getId(), userDTO.getName(), userDTO.getSurname(), R.drawable.profile1, userDTO.getTelephoneNumber(), userDTO.getEmail(),
                userDTO.getAddress()," ", userDTO.isActive(), userDTO.isBlocked());
    }

    public Passenger(int id, String name, String surname, int profilePic, String phone, String email, String address, String password, boolean active, boolean blocked) {
        super(id, name, surname, profilePic, phone, email, address, password, active, blocked);
        this.rides = new ArrayList<Ride>();
        this.panics = new ArrayList<Panic>();
        this.payments = new ArrayList<Payment>();
        this.routes = new ArrayList<Route>();
        this.favouriteRoutes = new ArrayList<FavouriteRoute>();
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public List<Panic> getPanics() {
        return panics;
    }

    public void setPanics(List<Panic> panics) {
        this.panics = panics;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<FavouriteRoute> getFavouriteRoutes() {
        return favouriteRoutes;
    }

    public void setFavouriteRoutes(List<FavouriteRoute> favouriteRoutes) {
        this.favouriteRoutes = favouriteRoutes;
    }
}
