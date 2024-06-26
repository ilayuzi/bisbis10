package com.att.tdp.bisbis10.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RestaurantsResponseDto {

    private Integer id;

    @NotEmpty
    @NotNull
    private String name;

    private double averageRating;

    @NotNull
    private boolean kosher;

    private List<String> cuisines;
}
