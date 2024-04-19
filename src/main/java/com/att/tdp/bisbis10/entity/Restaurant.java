package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "Restaurants")
public class Restaurant {

    @Id // primary key
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurants_seq"
    )
    @SequenceGenerator(
            name = "restaurants_seq",
            sequenceName = "restaurants_seq",
            allocationSize = 1

    )
    private Integer id;

    @NotNull
    private String name;

    private double averageRating = getAverageRating();

    @NotNull
    private boolean isKosher;

//    @ElementCollection
//    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
//    @Column(name = "cuisine")
//    private Set<String> cuisines = new HashSet<>();
//    private List<String> cuisines;

    @ManyToMany
    @JoinTable(
            name = "restaurant_cuisine",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id")
    )
    @JsonManagedReference

//    @OneToMany(mappedBy = "restaurant")
//    @JsonManagedReference
    private List<Cuisine> cuisines;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<Dish> dishes;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<Rating> ratings;


    // Constructor
    public Restaurant() {
    }

    public double getAverageRating () {
        if(ratings == null || ratings.isEmpty()){
            return 0.0;
        }
        var averageRating = this.ratings.stream().mapToDouble(Rating::getRating).average().orElse(0.0);
        String roundedAverageRating = String.format("%.2f", averageRating);
        return Double.parseDouble(roundedAverageRating);

    }

}

