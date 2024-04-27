package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    private float rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;


    public Rating() {
    }

}
