package com.example.easygo.model.users;

import com.example.easygo.dto.UserDTO;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Vehicle;
import com.example.easygo.model.WorkingHours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Driver extends User implements Serializable {
    private String driverLicense;
    private String vehicleRegistration;
    private Vehicle vehicle;
    private List<Ride> rides;
    private List<WorkingHours> workingHours;

    public Driver() {};

    public Driver(User user) {
        super(user);
    }

    public Driver(UserDTO userDTO){
        super(userDTO.getId(), userDTO.getName(), userDTO.getSurname(), 0, userDTO.getProfilePicture(), userDTO.getTelephoneNumber(), userDTO.getEmail(),
                userDTO.getAddress()," ", userDTO.isActive(), userDTO.isBlocked());
    }

    public Driver(int id, String name, String surname, int profilePic, String profilePicture, String phone, String email, String address, String password, boolean blocked, boolean active, String driverLicense, String vehicleRegistration, Vehicle vehicle) {
        super(id, name, surname, profilePic, profilePicture, phone, email, address, password, active, blocked);
        this.driverLicense = driverLicense;
        this.vehicleRegistration = vehicleRegistration;
        this.vehicle = vehicle;
        this.rides = new ArrayList<Ride>();
        this.workingHours = new ArrayList<WorkingHours>();
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public List<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHours> workingHours) {
        this.workingHours = workingHours;
    }

    public boolean isAvailable(Ride newRide) {
        if (!this.isActive())
            return false;

        // Checking for rides
        for (Ride ride : this.rides) {
            if (this.isBusy(ride, newRide))
                return false;
        }

        return true;
    }

    private boolean isBusy(Ride ride, Ride newRide) {
        return ride.getStartTime().isBefore(newRide.getStartTime().plusMinutes(newRide.getEstimatedTime()))
                && newRide.getStartTime().isBefore(ride.getStartTime().plusMinutes(ride.getEstimatedTime()));
    }


    public boolean compatibileVehicle(Ride rideDTO) {
        if (!rideDTO.getVehicleType().equals(this.vehicle.getVehicleName()))
            return false;
        if (rideDTO.isPetTransport() && !this.vehicle.isPetTransport())
            return false;
        return !rideDTO.isBabyTransport() || this.vehicle.isBabyproof();
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
