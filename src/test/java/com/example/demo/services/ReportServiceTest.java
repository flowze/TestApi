package com.example.demo.services;

import com.example.demo.dto.DailyReportDto;
import com.example.demo.dto.MealDto;
import com.example.demo.models.Meal;
import com.example.demo.models.User;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private CalorieCalculatorService calorieCalculatorService;

    @InjectMocks
    private ReportService reportService;

    @Test
    void generateDailyReport_UserNotFound_ShouldThrowException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                ()-> reportService.generateDailyReport(1L, LocalDate.now()));
    }

    @Test
    void generateDailyReport_ShouldReturnCorrectReport() {
        User user = new User();
        user.setId(1L);
        List<Meal> meals = List.of(new Meal(), new Meal());
        LocalDate testDate = LocalDate.of(2000,3,1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserIdandDate(1L, testDate)).thenReturn(meals);
        when(calorieCalculatorService.calculateTotalCalories(meals)).thenReturn(2000.0);

        DailyReportDto result = reportService.generateDailyReport(1L, testDate);

        assertNotNull(result);
        assertEquals(testDate, result.getDate());
        assertEquals(2000.0, result.getTotalCalories());
        assertEquals(meals.size(), result.getMeals().size());

    }

    @Test
    void checkCalorieNorm_UnderLimit_ShouldReturnTrue() {
        User user = new User();
        user.setDailyCalorieNorm(2000.0);
        List<Meal> meals = List.of(new Meal(), new Meal());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserIdandDate(1L, LocalDate.now())).thenReturn(meals);
        when(calorieCalculatorService.calculateTotalCalories(meals)).thenReturn(1500.0);

        assertTrue(reportService.checkCalorieNorm(1L, LocalDate.now()));

    }

    @Test
    void checkCalorieNorm_OverLimit_ShouldReturnFalse() {
        User user = new User();
        user.setDailyCalorieNorm(2000.0);
        List<Meal> meals = List.of(new Meal(), new Meal());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserIdandDate(1L, LocalDate.now())).thenReturn(meals);
        when(calorieCalculatorService.calculateTotalCalories(meals)).thenReturn(2500.0);

        assertFalse(reportService.checkCalorieNorm(1L, LocalDate.now()));

    }

    @Test
    void getMealHistory_ShouldReturnOrderedList() {
        User user = new User();
        Meal meal1 = new Meal();
        meal1.setDate(LocalDate.now().minusDays(1));
        Meal meal2 = new Meal();
        meal2.setDate(LocalDate.now());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(mealRepository.findByUserOrderByDateDesc(user)).thenReturn(List.of(meal2, meal1));

        List<MealDto> result = reportService.getMealHistory(1L);

        assertEquals(2, result.size());
        assertTrue(result.get(0).getDate().isAfter(result.get(1).getDate()));
    }
}