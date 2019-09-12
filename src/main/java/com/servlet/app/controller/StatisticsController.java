package com.servlet.app.controller;

import com.servlet.app.services.StatisticsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    @NonNull
    private StatisticsService statisticsService;

    @GetMapping(value = "/statistics")
    public ResponseEntity<Void> getStatistics() {

        statisticsService.getStatistics();
        return ResponseEntity.ok().build();
    }
}