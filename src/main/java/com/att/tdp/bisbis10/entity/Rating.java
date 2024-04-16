package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Ratings")
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    private float rating;

    public Rating(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
