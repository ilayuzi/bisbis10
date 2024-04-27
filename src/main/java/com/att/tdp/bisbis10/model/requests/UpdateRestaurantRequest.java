package com.att.tdp.bisbis10.model.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateRestaurantRequest {
    private String name;
    private Boolean isKosher;
    private List<String> cuisines;

}
