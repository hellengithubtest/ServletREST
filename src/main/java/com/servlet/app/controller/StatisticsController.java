package com.servlet.app.controller;

import com.servlet.app.services.StatisticsService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class StatisticsController {
    @NonNull
    private StatisticsService statisticsService;


    @GetMapping(value = "/statistics")
    public ResponseEntity<Void> getStatistics() {
        try {
            statisticsService.getStatistics();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
