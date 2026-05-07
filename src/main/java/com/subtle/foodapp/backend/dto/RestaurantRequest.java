package com.subtle.foodapp.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantRequest{

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    private Double rating;
}
