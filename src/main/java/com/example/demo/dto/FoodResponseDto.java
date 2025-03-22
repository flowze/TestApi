package com.example.demo.dto;

import lombok.Data;

@Data
public class FoodResponseDto {
    private String name;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
}