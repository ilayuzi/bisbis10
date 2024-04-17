package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    private float averageRating;

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
    private List<Cuisine> cuisines;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<Dish> dishes;

    // Constructor
    public Restaurant() {
    }

//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public float getRating() {
//        return rating;
//    }
//
//    public void setRating(float rating) {
//        this.rating = rating;
//    }
//
//    public boolean isKosher() {
//        return isKosher;
//    }
//
//    public void setKosher(boolean kosher) {
//        isKosher = kosher;
//    }
//
//    public Set<String> getCuisines() {
//        return cuisines;
//    }
//
//    public void setCuisines(Set<String> cuisines) {
//        this.cuisines = cuisines;
//    }

//    public List<Cuisine> getCuisines() {
//        return cuisines;
//    }
//
//    public void setCuisines(List<Cuisine> cuisines) {
//        this.cuisines = cuisines;
//    }

}

