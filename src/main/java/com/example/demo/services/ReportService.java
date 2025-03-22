package com.example.demo.services;

import com.example.demo.dto.DailyReportDto;
import com.example.demo.dto.MealDto;
import com.example.demo.models.Meal;
import com.example.demo.models.User;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final CalorieCalculatorService calorieCalculatorService;
    private final ModelMapper modelMapper;
    @SneakyThrows
    public DailyReportDto generateDailyReport(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
        if(date ==null){
            date = LocalDate.now();
        }
        List<Meal> meals = mealRepository.findByUserIdandDate(userId,date);

        return DailyReportDto.builder()
                .date(date)
                .totalCalories(calorieCalculatorService.calculateTotalCalories(meals))
                .meals(mapToMealDtos(meals))
                .build();
    }

    private List<MealDto> mapToMealDtos(List<Meal> meals) {
        return meals.stream()
                .map(meal -> new MealDto(
                        meal.getDate(),
                        meal.getItems()
                ))
                .toList();

    }


    @SneakyThrows
    public Boolean checkCalorieNorm(Long userId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
        if(date ==null){
            date = LocalDate.now();
        }
        List<Meal> meals = mealRepository.findByUserIdandDate(userId,date);
        double total = calorieCalculatorService.calculateTotalCalories(meals);
        return total <= user.getDailyCalorieNorm();
    }

    @SneakyThrows
    public List<MealDto> getMealHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
        List<Meal> meals = mealRepository.findByUserOrderByDateDesc(user);
        return mapToMealDtos(meals);
    }
}