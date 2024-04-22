package com.att.tdp.bisbis10.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table (name="Cuisines")
public class Cuisine {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cuisines_seq"
    )
    @SequenceGenerator(
            name = "cuisines_seq",
            sequenceName = "cuisines_seq",
            allocationSize = 1

    )
    private Integer id;

    @Column(unique = true)
    @NotBlank
    private String cuisineName;

    @ManyToMany(mappedBy = "cuisines")
    @JsonBackReference
    private List<Restaurant> restaurants = new ArrayList<>();

    public Cuisine() {

    }



}
