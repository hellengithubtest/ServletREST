package com.servlet.app.services;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import com.servlet.app.repository.CarRepository;
import com.servlet.app.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    PersonService personService;

    public Long countCar() {
        return carRepository.count();
    }

    public Long countModelDistinct() {
        return carRepository.countByUniqueName();
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveCar(Car car) {
        carRepository.findById(car.getId()).ifPresent(e -> {
            throw new EntityExistsException("The car is exit");
        });
        Person person = car.getOwner();
        carRepository.save(car);
    }

    public void clearCar() {
        carRepository.deleteAll();
    }

    public Car [] findByOwner(Long owner) {
        return carRepository.findByOwner(owner);
    }
}
