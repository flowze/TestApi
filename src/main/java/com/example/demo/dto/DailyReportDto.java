package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DailyReportDto {
    private LocalDate date;
    private double totalCalories;
    private List<MealDto> meals;
}