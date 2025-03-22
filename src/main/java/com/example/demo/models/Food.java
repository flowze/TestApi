package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "foods")
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @DecimalMin(value = "0.0")
    private double caloriesPerPortion;

    @DecimalMin(value = "0.0")
    private double proteins;

    @DecimalMin(value = "0.0")
    private double fats;

    @DecimalMin(value = "0.0")
    private double carbohydrates;


    public String toString() {
        return "Food(id=" + this.getId() + ", name=" + this.getName() + ", caloriesPerPortion=" + this.getCaloriesPerPortion() + ", proteins=" + this.getProteins() + ", fats=" + this.getFats() + ", carbohydrates=" + this.getCarbohydrates() + ")";
    }
}
