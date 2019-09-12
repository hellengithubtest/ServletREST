package com.servlet.app.services;

import com.servlet.app.dto.CarDto;
import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.repository.CarRepository;
import com.servlet.app.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonWithCarsService {

    @Autowired
    PersonService personService;
    @Autowired
    CarService carService;

    @Transactional(rollbackFor = Exception.class)
    public PersonWithCars getPersonWithCars(Long id) {
        PersonWithCars personWithCars = new PersonWithCars();
        Person person = personService.findById(id);

        List<CarDto> carDtoList = new ArrayList<CarDto>();
        carDtoList= carService.findByOwner(person).stream().map(car -> {
            CarDto carDto = new CarDto(car.getId(), car.getModel(), car.getHorsepower(),car.getOwner().getId());
            return carDto;
        }).collect(Collectors.toList());

        personWithCars.setCars(carDtoList.stream().toArray(CarDto[]::new));
        personWithCars.setId(person.getId());
        personWithCars.setName(person.getName());
        personWithCars.setBirthdate(person.getBirthdate());
        return personWithCars;
    }


}
