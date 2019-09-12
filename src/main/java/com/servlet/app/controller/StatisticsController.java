package com.servlet.app.controller;

import com.servlet.app.dto.Statistics;
import com.servlet.app.services.StatisticsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    @NonNull
    private StatisticsService statisticsService;

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<Statistics> getStatistics() {
        return ResponseEntity.ok().body(statisticsService.getStatistics());
    }
}