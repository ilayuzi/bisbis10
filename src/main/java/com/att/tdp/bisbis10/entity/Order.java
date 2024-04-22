package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Order_seq"
    )
    @SequenceGenerator(
            name = "Order_seq",
            sequenceName = "Order_seq",
            allocationSize = 1
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;


//    @Embedded
//    @ElementCollection
//    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
//    @Column(name = "orderItem")
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderItem> orderItems;
}


