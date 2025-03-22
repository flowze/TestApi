package com.example.demo.controllers;

import com.example.demo.dto.FoodRequestDto;
import com.example.demo.dto.FoodResponseDto;
import com.example.demo.services.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @PostMapping()
    public ResponseEntity<FoodResponseDto> createFood(@Valid @RequestBody FoodRequestDto foodRequest) {
        FoodResponseDto food = foodService.createFood(foodRequest);
        return ResponseEntity.ok(food);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getFood(@PathVariable Long id){
        FoodResponseDto food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }
}
