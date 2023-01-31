package com.example.easygo.model;

import com.example.easygo.model.users.Driver;
import com.example.easygo.utility.Convert;

import java.time.LocalDateTime;

public class WorkingHours {
    private int id;
    private int[] start;
    private int[] end;
    private Driver driver;

    public WorkingHours() {}

    public WorkingHours(WorkingHours workingHours){
        this.id = workingHours.id;
        this.start = workingHours.start;
        this.end = workingHours.end;
        this.driver = workingHours.driver;
    }

    public WorkingHours(LocalDateTime start, LocalDateTime end) {
        this.start = Convert.toIntArray(start);
        this.end = Convert.toIntArray(end);
    }

    public LocalDateTime getStart() {
        return Convert.toLocalDateTime(this.start);
    }

    public void setStart(LocalDateTime start) {
        this.start = Convert.toIntArray(start);
    }

    public LocalDateTime getEnd() { return Convert.toLocalDateTime(this.end); }

    public void setEnd(LocalDateTime end) {
        this.end = Convert.toIntArray(end);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
