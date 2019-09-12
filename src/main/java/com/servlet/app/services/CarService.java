package com.servlet.app.services;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import com.servlet.app.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;

@Validated
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    PersonService personService;

    @Transactional(rollbackFor = Exception.class)
    public Long countCar() {
        return carRepository.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long countModelDistinct() {
        return carRepository.countByUniqueName();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCar(@Valid  Car car) {
        carRepository.findById(car.getId()).ifPresent(e -> {
            throw new EntityExistsException("The car is exit");
        });
        carRepository.save(car);
    }

    @Transactional(rollbackFor = Exception.class)
    public void clearCar() {
        carRepository.deleteAll();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Car> findByOwner(Person owner) {
        return carRepository.findByOwner(owner);
    }
}
