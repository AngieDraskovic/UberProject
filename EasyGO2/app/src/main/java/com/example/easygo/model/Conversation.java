package com.example.easygo.model;

import com.example.easygo.model.users.User;

import java.util.ArrayList;

public class Conversation {

    private User anotherUser;
    private String lastMessage;
    private ArrayList<Message> messages;

    public Conversation() {
        this.messages = new ArrayList<Message>();
    }

    public Conversation(User anotherUser, String lastMessage, ArrayList<Message> messages) {
        this();
        this.anotherUser = anotherUser;
        this.lastMessage = lastMessage;
        this.messages = messages;
    }

    public User getAnotherUser() {
        return anotherUser;
    }

    public void setAnotherUser(User anotherUser) {
        this.anotherUser = anotherUser;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
