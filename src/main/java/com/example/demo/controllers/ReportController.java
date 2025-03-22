package com.example.demo.controllers;

import com.example.demo.dto.DailyReportDto;
import com.example.demo.dto.MealDto;
import com.example.demo.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @GetMapping("/daily/{userId}")
    public ResponseEntity<DailyReportDto> getDailyReport(@PathVariable Long userId,
                                                         @RequestParam(required = false) LocalDate date){
        return ResponseEntity.ok(reportService.generateDailyReport(userId,date));
    }

    @GetMapping("/check/{userId}")
    public ResponseEntity<Boolean> checkCalorieNorm(@PathVariable Long userId,
                                                    @RequestParam(required = false) LocalDate date){
        return ResponseEntity.ok(reportService.checkCalorieNorm(userId, date));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MealDto>> getMealHistory(@PathVariable Long userId){
        return ResponseEntity.ok(reportService.getMealHistory(userId));
    }
}
