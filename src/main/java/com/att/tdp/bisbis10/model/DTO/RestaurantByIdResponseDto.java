package com.att.tdp.bisbis10.model.DTO;

import com.att.tdp.bisbis10.entity.Dish;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantByIdResponseDto extends RestaurantsResponseDto {
    private List<Dish> dishes;
}
