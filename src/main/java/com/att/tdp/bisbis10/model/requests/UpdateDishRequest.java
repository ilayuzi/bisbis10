package com.att.tdp.bisbis10.model.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDishRequest {
    private String name;
    private String description;
    private Double price;
}
