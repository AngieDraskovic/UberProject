package com.example.easygo.model;

public class Location {
    private int id;
    private double lenght;
    private double width;

    public Location() {};

    public Location(int id, double lenght, double width) {
        this.id = id;
        this.lenght = lenght;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
