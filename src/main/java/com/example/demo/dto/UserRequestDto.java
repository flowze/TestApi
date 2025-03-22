package com.example.demo.dto;

import com.example.demo.models.Gender;
import com.example.demo.models.Goal;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Min(1)
    private int age;

    @DecimalMin(value = "0.1")
    private double weight;

    @DecimalMin(value = "0.1")
    private double height;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
