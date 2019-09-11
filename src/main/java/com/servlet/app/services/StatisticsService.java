package com.servlet.app.services;

import com.servlet.app.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatisticsService {
    @Autowired
    private CarService carService;

    @Autowired
    private PersonService personService;

    @Transactional(rollbackFor = Exception.class)
    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        statistics.setPersoncount(personService.countPerson());
        statistics.setCarcount(carService.countCar());
        statistics.setUniquevendorcount(carService.countModelDistinct());
        return statistics;
    }
}
