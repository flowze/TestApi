package com.example.demo.controllers;

import com.example.demo.dto.MealDto;
import com.example.demo.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<MealDto> createMeal(@PathVariable Long userId,
                                              @RequestBody MealDto mealDto) {
        MealDto meal = mealService.createMeal(userId, mealDto);
        return ResponseEntity.ok(meal);
    }
}