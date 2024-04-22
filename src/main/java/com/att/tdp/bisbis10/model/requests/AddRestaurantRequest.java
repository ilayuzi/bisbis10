package com.att.tdp.bisbis10.model.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddRestaurantRequest {
    private String name;
    private boolean isKosher;
    private List<String> cuisines;
}
