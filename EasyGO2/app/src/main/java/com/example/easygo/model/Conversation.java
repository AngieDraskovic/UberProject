package com.example.easygo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.easygo.model.users.User;

import java.util.ArrayList;

public class Conversation implements Parcelable {

    private User anotherUser;
    private String lastMessage;
    private ArrayList<Message> messages;
    private Message lastMessageObject;          // dodala sam da bismo mogli izvuci vrijeme posljednje poruke
//    private int anotherUserId;

    public Conversation() {
        this.messages = new ArrayList<Message>();
    }

    public Conversation(Integer anotherUserId, String lastMessage, ArrayList<Message> messages, Message lastMessageObject) {
        this();
//        this.anotherUserId = anotherUserId;
        this.lastMessage = lastMessage;
        this.messages = messages;
        this.lastMessageObject = lastMessageObject;
    }

    public Conversation(User anotherUser, String lastMessage, ArrayList<Message> messages, Message lastMessageObject) {
        this();
        this.anotherUser = anotherUser;
        this.lastMessage = lastMessage;
        this.messages = messages;
        this.lastMessageObject = lastMessageObject;
    }

    protected Conversation(Parcel in) {
        anotherUser = in.readParcelable(User.class.getClassLoader());
        lastMessage = in.readString();
        messages = in.createTypedArrayList(Message.CREATOR);
        lastMessageObject = in.readParcelable(Message.class.getClassLoader());
    }

    public static final Creator<Conversation> CREATOR = new Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel in) {
            return new Conversation(in);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };

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


    public Message getLastMessageObject() {
        return lastMessageObject;
    }

    public void setLastMessageObject(Message lastMessageObject) {
        this.lastMessageObject = lastMessageObject;
    }

//    public int getAnotherUserId() {
//        return anotherUserId;
//    }
//
//    public void setAnotherUserId(int anotherUserId) {
//        this.anotherUserId = anotherUserId;
//    }

    @Override
    public String toString() {
        StringBuilder conversationStr = new StringBuilder();
        for (Message message : messages) {
            if (message.getSender().equals(anotherUser))
                conversationStr.append(anotherUser).append(": ").append(message.getText()).append("\n");
            else
                conversationStr.append("Me: ").append(message.getText()).append("\n");
        }
        return conversationStr.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(anotherUser, i);
        parcel.writeString(lastMessage);
        parcel.writeTypedList(messages);
        parcel.writeParcelable(lastMessageObject, i);
    }
}
