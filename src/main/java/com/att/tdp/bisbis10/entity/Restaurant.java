package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Restaurants")
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "restaurant_id")
    private float rating;

    private boolean isKosher;

    @ElementCollection
//    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
//    @Column(name = "cuisine")
    private Set<String> cuisines = new HashSet<>();
//    private List<String> cuisines;


//    @ManyToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "restaurants_ratings",
//            joinColumns = @JoinColumn(
//                    name = "restaurant_id",
//                    referencedColumnName = "id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "rating_id",
//                    referencedColumnName = "id"
//            )
//    )
//    private List<Rating> ratings;

    // Constructor
    public Restaurant() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public Set<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<String> cuisines) {
        this.cuisines = cuisines;
    }

//    public List<Cuisine> getCuisines() {
//        return cuisines;
//    }
//
//    public void setCuisines(List<Cuisine> cuisines) {
//        this.cuisines = cuisines;
//    }

}

