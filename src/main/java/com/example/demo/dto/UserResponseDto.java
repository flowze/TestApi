package com.example.demo.dto;

import com.example.demo.models.Gender;
import com.example.demo.models.Goal;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private int age;
    private double weight;
    private double height;
    private Goal goal;
    private Gender gender;
    private double dailyCalorieNorm;
}