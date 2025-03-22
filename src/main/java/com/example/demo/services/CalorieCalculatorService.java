package com.example.demo.services;

import com.example.demo.models.Gender;
import com.example.demo.models.Goal;
import com.example.demo.models.Meal;
import com.example.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalorieCalculatorService {

    public User calculateDailyCalories(User user) {
        double calorieNorm = calculatedCalorieNorm(user);
        double adjusted = adjustByGoal(calorieNorm, user.getGoal());
        user.setDailyCalorieNorm(adjusted);
        return user;
    }

    private double adjustByGoal(double calorieNorm, Goal goal) {
        if(goal == Goal.WEIGHT_LOSS){
            return calorieNorm * 0.85;
        } else if (goal == Goal.WEIGHT_GAIN) {
            return calorieNorm * 1.15;
        }
        return calorieNorm;
    }

    private double calculatedCalorieNorm(User user) {
        if(user.getGender() == Gender.MALE){
            return 66.47 + (13.75 * user.getWeight())
                    + (5.003 * user.getHeight()) - (6.755 * user.getAge());
        }else{
            return 655.1 + (9.563 * user.getWeight())
                    + (1.850 * user.getHeight()) - (4.676 * user.getAge());
        }
    }

    public double calculateTotalCalories(List<Meal> meals){
        return meals.stream()
                .mapToDouble(this::calculateItemCalories)
                .sum();
    }

    private double calculateItemCalories(Meal meal) {
        return meal.getItems().stream()
                .mapToDouble(item ->item.getFood().getCaloriesPerPortion() * item.getPortionCount() )
                .sum();
    }
}
