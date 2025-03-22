package com.example.demo.repositories;

import com.example.demo.models.Meal;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query(value = "SELECT * FROM meals WHERE user_id = :userId AND date = :date", nativeQuery = true)
    List<Meal> findByUserIdandDate(@Param("userId") Long userId, @Param("date") LocalDate date);

    List<Meal> findByUserOrderByDateDesc(User user);
}
