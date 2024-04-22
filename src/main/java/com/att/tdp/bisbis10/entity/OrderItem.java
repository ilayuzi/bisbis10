package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderItems")
//@Embeddable
public class OrderItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "OrderItem_seq"
    )
    @SequenceGenerator(
            name = "OrderItem_seq",
            sequenceName = "OrderItem_seq",
            allocationSize = 1
    )
    private Integer id;

    @NotNull
    private Integer dishId;

    @NotNull
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
