package com.example.easygo.dto;

import com.example.easygo.model.Review;

public class ReviewDTO {
    private Integer id;
    private Integer rating;
    private String comment;
    private PassengerDTOResponse passenger;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.rating =  5;                           // TODO: Konvertuj iz double u Integer
        this.comment = review.getComment();
        //this.passenger = review.getPassenger();                               // TODO
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PassengerDTOResponse getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerDTOResponse passenger) {
        this.passenger = passenger;
    }
}
