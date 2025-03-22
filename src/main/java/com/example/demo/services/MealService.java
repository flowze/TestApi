package com.example.demo.services;

import com.example.demo.dto.MealDto;
import com.example.demo.models.Meal;
import com.example.demo.models.User;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MealService {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;


    @SneakyThrows
    public MealDto createMeal(Long userId, MealDto mealDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
        Meal meal = new Meal();
        meal.setDate(mealDto.getDate());
        meal.setUser(user);
        meal.setItems(mealDto.getItems());
        mealRepository.save(meal);
        return modelMapper.map(meal,MealDto.class);
    }
}