package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminator;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ratings_seq"
    )
    @SequenceGenerator(
            name = "ratings_seq",
            sequenceName = "ratings_seq",
            allocationSize = 1

    )
    private Integer id;

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

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;

//    @JsonProperty("restaurant_id")
//    private Integer restaurantId = restaurant.getId();

    public Rating(){
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
