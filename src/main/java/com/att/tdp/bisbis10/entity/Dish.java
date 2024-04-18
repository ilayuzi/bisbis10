package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Dishes")
public class Dish {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dishes_seq"
    )
    @SequenceGenerator(
            name = "dishes_seq",
            sequenceName = "dishes_seq",
            allocationSize = 1
    )
    private Integer id;

    private String name;

    private String description;

    private float price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;

    public Dish() {

    }
}
