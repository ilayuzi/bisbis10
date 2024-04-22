package com.att.tdp.bisbis10.model.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddRatingRequest {
    private Integer restaurantId;
    private Double rating;
}
