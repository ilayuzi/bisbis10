package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Ratings")
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

//    @Column(name = "restaurant_id")
//    private Long restaurantId;

    private float rating;

//    @ManyToMany(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            mappedBy = "ratings"
//    )
//    private List<Restaurant> restaurants;

//    @ManyToOne
//    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
//    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Rating(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getRestaurantId() {
//        return restaurantId;
//    }
//
//    public void setRestaurantId(Long restaurantId) {
//        this.restaurantId = restaurantId;
//    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
