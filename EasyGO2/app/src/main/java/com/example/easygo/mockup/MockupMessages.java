package com.example.easygo.mockup;

import com.example.easygo.LoggedIn;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.Message;
import com.example.easygo.model.Ride;
import com.example.easygo.model.enumerations.MessaggeType;
import com.example.easygo.model.users.Driver;
import com.example.easygo.model.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MockupMessages {

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        // Tipa je User jer je tako navedeno u dijagramu, ali realno za razmjenu poruka nam i ne trebaju neki detaljniji podaci
        User driver1 = MockupDrivers.getDrivers().get(1);
        User driver2 = MockupDrivers.getDrivers().get(2);
        User driver3 = MockupDrivers.getDrivers().get(3);

        User passenger1 =  MockupPassengers.getPassengers().get(4);
        User passenger2 =  MockupPassengers.getPassengers().get(5);
        User passenger3 =  MockupPassengers.getPassengers().get(6);

        Ride ride1 = MockupRides.getRides().get(1);
        Ride ride2 = MockupRides.getRides().get(2);
        Ride ride3 = MockupRides.getRides().get(3);

        /*
        Za ride1: putnik pita vozaca da li ostaje dogovor i vozac potvrdjuje.
        Najbitnije je da se proslijede sender i deliverer sto su u ovom slucaju passenger1 i driver1, kao i voznja na koju se poruka odnosi
        Ostalo je isto u svim porukama: tekst, vrijeme, tip poruke.
         */


        Message ride1Message1 = new Message(1, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger1, driver1, ride1);
        Message ride1Message2 = new Message(2, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver1, passenger1, ride1);
        Message ride1Message3 = new Message(10, "U redu", LocalDateTime.now(), MessaggeType.RIDE, passenger1, driver2, ride1);
        Message ride1Message4 = new Message(11, "Voznja je spremna.", LocalDateTime.now(), MessaggeType.RIDE, driver2, passenger1, ride1);
        Message ride1Message5 = new Message(12, "Zelim da napustim voznju!", LocalDateTime.now(), MessaggeType.PANIC, passenger1, driver3, ride1);
        Message ride1Message6 = new Message(13, "Zaustavljam voznju!", LocalDateTime.now(), MessaggeType.PANIC, driver3, passenger1, ride1);        /*
        Za ride2: Imamo 2 putnika u drugoj voznji i obojica pitaju vozaca isto pitanje.
         */
        Message ride2Message1 = new Message(4, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger2, driver2, ride2);
        Message ride2Message2 = new Message(5, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver2, passenger2, ride2);
        Message ride2Message3 = new Message(6, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger3, driver2, ride2);
        Message ride2Message4 = new Message(7, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver2, passenger3, ride2);


        /*
        Za ride3: Isto kao i za ride1 samo drugi vozac
         */
        Message ride3Message1 = new Message(8, "Da li ostaje dogovor?", LocalDateTime.now(), MessaggeType.RIDE, passenger1, driver3, ride3);
        Message ride3Message2 = new Message(9, "Dogovor ostaje.", LocalDateTime.now(), MessaggeType.RIDE, driver3, passenger1, ride3);


        messages.add(ride2Message1);
        messages.add(ride2Message2);
        messages.add(ride2Message3);
        messages.add(ride2Message4);
        messages.add(ride3Message1);
        messages.add(ride3Message2);
        messages.add(ride1Message1);
        messages.add(ride1Message2);
        messages.add(ride1Message3);
        messages.add(ride1Message4);
        messages.add(ride1Message5);
        messages.add(ride1Message6);


        return messages;
    }

    public static ArrayList<Conversation> getCurrUserMessages() {
        User currUser = LoggedIn.getUser();
        HashMap<User, ArrayList<Message>> messagesMap = new HashMap<User, ArrayList<Message>>();
        ArrayList<Conversation> conversations = new ArrayList<Conversation>();

        for (Message message : getMessages()) {
            if (message.getSender().equals(currUser) || message.getDeliverer().equals(currUser)) {
                User anotherUser = (message.getSender().equals(currUser)) ? message.getDeliverer() : message.getSender();

                if (messagesMap.containsKey(anotherUser))
                    messagesMap.get(anotherUser).add(message);
                else {
                    messagesMap.put(anotherUser, new ArrayList<Message>());
                    messagesMap.get(anotherUser).add(message);
                }
            }
        }

        for (HashMap.Entry<User, ArrayList<Message>> set : messagesMap.entrySet()) {
            String lastMessage = set.getValue().get(set.getValue().size()-1).getText();
            conversations.add(new Conversation(set.getKey(), lastMessage, set.getValue(), set.getValue().get(set.getValue().size()-1)));
        }

        return conversations;

    }

}
