package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodRequestDto {
    @NotBlank
    private String name;

    @DecimalMin(value = "0.0")
    private double calories;

    @DecimalMin(value = "0.0")
    private double proteins;

    @DecimalMin(value = "0.0")
    private double fats;

    @DecimalMin(value = "0.0")
    private double carbohydrates;
}