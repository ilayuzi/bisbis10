package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@Table(name = "Dishes")
@NoArgsConstructor
@AllArgsConstructor
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


    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private double price;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;


}
