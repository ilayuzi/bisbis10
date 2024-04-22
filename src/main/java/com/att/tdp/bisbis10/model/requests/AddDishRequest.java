package com.att.tdp.bisbis10.model.requests;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AddDishRequest {

    private String name;
    private Integer price;
    private String description;

}
