package com.example.easygo.mockup;

import com.example.easygo.model.users.Passenger;

import java.util.HashMap;
import com.example.easygo.R;

public class MockupPassengers {

    private static int lastId;
    private static HashMap<Integer, Passenger> passengersMap;

    public static HashMap<Integer, Passenger> getPassengers() {
        if (passengersMap != null)
            return passengersMap;

        passengersMap = new HashMap<Integer, Passenger>();

        Passenger passenger1 = new Passenger(4, "Ivana", "Simic", R.drawable.profile1, "061-032-3230", "ivanasimic@gmail.com", "Svetog Save 1", "ivana123", true, false);
        Passenger passenger2 = new Passenger(5, "Sale", "Katai", R.drawable.sale_katai, "062-032-3230", "salekatai@gmail.com", "Svetog Save 2", "sale123", true, false);
        Passenger passenger3 = new Passenger(6, "Osman", "Bukari", R.drawable.osman_bukari, "063-032-3230", "osmanbukari@gmail.com", "Svetog Save 3", "osman123", true, false);

        passengersMap.put(passenger1.getId(), passenger1);
        passengersMap.put(passenger2.getId(), passenger2);
        passengersMap.put(passenger3.getId(), passenger3);
        lastId = 6;

        return passengersMap;
    }

    public static Passenger findPassenger(String email, String password) {
        for (Passenger passenger : getPassengers().values())
            if (passenger.getEmail().equals(email) && passenger.getPassword().equals(password))
                return passenger;

        return null;
    }


    /*  Po meni je atribut id bespotreban jer je email taj koji je jedinstven za sve korisnike, ali tako stoji u njihovom dijagramu.
        Ovako cuvamo poslednji id passenger-a (lastId) tako da cemo novi id dobiti tako sto inkrementiramo lastId za jedan.
        Mnogo je jednostavnije sa email-om da se radi, ali to cemo vjerovatno kad budemo sa bazom radili, ovo su ipak probni podaci.
     */
    public static int generateId() {
        return ++lastId;
    }

    public static void addNew(Passenger newPassenger) {
        passengersMap.put(newPassenger.getId(), newPassenger);
    }
}
