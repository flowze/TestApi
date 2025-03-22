package com.example.demo.dto;

import com.example.demo.models.MealItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class MealDto {

    private LocalDate date;
    private List<MealItem> items;


}
