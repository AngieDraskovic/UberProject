package com.example.easygo.model;

import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class Remark {

    private int id;
    private String message;
    private int[] date;
    private int userId;

    public Remark(){}

    public Remark(String message, LocalDateTime date, int userId) {
        this.message = message;
        this.date = Convert.toIntArray(date);
        this.userId = userId;
    }

    public Remark(int id, String message, LocalDateTime date, int userId) {
        this.id = id;
        this.message = message;
        this.date = Convert.toIntArray(date);
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return Convert.toLocalDateTime(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = Convert.toIntArray(date);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
