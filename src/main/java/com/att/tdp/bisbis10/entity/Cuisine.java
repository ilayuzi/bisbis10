package com.att.tdp.bisbis10.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table (name="Cuisines")
public class Cuisine {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String cuisineName;

    @ManyToMany(mappedBy = "cuisines")
    private List<Restaurant> restaurants;

    public Cuisine() {

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
//    public String getCuisineName() {
//        return cuisineName;
//    }
//
//    public void setCuisineName(String name) {
//        this.cuisineName = name;
//    }


}