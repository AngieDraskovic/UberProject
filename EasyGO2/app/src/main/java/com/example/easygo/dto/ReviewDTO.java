package com.example.easygo.dto;

import com.example.easygo.model.Review;
import com.example.easygo.model.users.User;

public class ReviewDTO {
    private int id;
    private Integer rating;
    private String comment;
    private UserDTO passenger;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.rating = review.getGrade();
        this.comment = review.getComment();
        //this.passenger = review.getPassenger();                               // TODO
    }
}
