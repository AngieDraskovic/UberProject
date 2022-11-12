package com.example.easygo.mockup;

import com.example.easygo.model.Message;
import com.example.easygo.model.Ride;
import com.example.easygo.model.enumerations.MessaggeType;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MockupMessages {

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        User driver1 = MockupDrivers.getDrivers().get(1);
        User driver2 = MockupDrivers.getDrivers().get(2);
        User driver3 = MockupDrivers.getDrivers().get(3);

        User passenger1 =  MockupPassengers.getPassengers().get(1);
        User passenger2 =  MockupPassengers.getPassengers().get(2);
        User passenger3 =  MockupPassengers.getPassengers().get(3);

        Ride ride1 = MockupRides.getRides().get(1);
        Ride ride2 = MockupRides.getRides().get(2);
        Ride ride3 = MockupRides.getRides().get(3);

        Message ride1Message1 = new Message(1, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger1, driver1, ride1);
        Message ride1Message2 = new Message(2, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver1, passenger1, ride1);

        Message ride2Message1 = new Message(4, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger2, driver2, ride2);
        Message ride2Message2 = new Message(5, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver2, passenger2, ride2);
        Message ride2Message3 = new Message(6, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger3, driver2, ride2);
        Message ride2Message4 = new Message(7, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver2, passenger3, ride2);

        Message ride3Message1 = new Message(8, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger1, driver3, ride3);
        Message ride3Message2 = new Message(9, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver3, passenger1, ride3);

        messages.add(ride1Message1);
        messages.add(ride1Message2);
        messages.add(ride2Message1);
        messages.add(ride2Message2);
        messages.add(ride2Message3);
        messages.add(ride2Message4);
        messages.add(ride3Message1);
        messages.add(ride3Message2);

        return messages;
    }

}
