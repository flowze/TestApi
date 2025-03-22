package com.example.demo.services;

import com.example.demo.dto.FoodRequestDto;
import com.example.demo.dto.FoodResponseDto;
import com.example.demo.models.Food;
import com.example.demo.repositories.FoodRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository, ModelMapper modelMapper) {
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;
    public FoodResponseDto createFood(FoodRequestDto foodRequest) {
        Food food = modelMapper.map(foodRequest, Food.class);
        foodRepository.save(food);
        return modelMapper.map(food,FoodResponseDto.class);
    }

    @SneakyThrows
    public FoodResponseDto getFoodById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Food not found"));
        return modelMapper.map(food, FoodResponseDto.class);
    }
}
