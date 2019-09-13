package com.servlet.app.services;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import com.servlet.app.services.PersonService;
import com.servlet.app.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityExistsException;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class CarService {
    @NonNull
    CarRepository carRepository;
    @NonNull
    PersonService personService;

    public Long countCar() {
        return carRepository.count();
    }

    public Long countModelDistinct() {
        return carRepository.countByUniqueName();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCar(@Valid Car car) {
        carRepository.findById(car.getId()).ifPresent(e -> {
            throw new EntityExistsException("The car is exit");
        });
        carRepository.save(car);
    }

    @Transactional(rollbackFor = Exception.class)
    public void clearCar() {
        carRepository.deleteAll();
    }

    public List<Car> findByOwner(Long owner) {
        return carRepository.findByOwnerId(owner);
    }
}
