package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id")
    private List<MealItem> items;

    public String toString() {
        return "Meal(id=" + this.getId() + ", user=" + this.getUser().getName() + ", date=" + this.getDate() + ", items=" + this.getItems() + ")";
    }
}