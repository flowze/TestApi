package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "meal_items")
@Data
public class MealItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    @JsonIgnore
    private Meal meal;

    @Min(value = 1)
    private int portionCount;

    @Override
    public String toString() {
        return "MealItem(id=" + this.getId() + ", food=" + this.getFood().getId() + ", meal=" + this.getMeal().getId() + ", portionCount=" + this.getPortionCount() + ")";
    }
}