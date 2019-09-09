package com.servlet.app.service;

import com.servlet.app.entity.Car;
import com.servlet.app.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService {

    CarRepository carRepository;

    public Long countCar() {
        return carRepository.count();
    }

    public Long countModelDistinct() {
        return carRepository.countByUniqueName();
    }

    public void  saveCar(Car car) {
        carRepository.save(car);
    }

    public void clearCar() {
        carRepository.deleteAll();
    }

    public Car [] findByOwnerId(Long ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }
}
