package com.servlet.app.mapper;

import com.servlet.app.dto.CarDto;
import com.servlet.app.entity.Car;
import com.servlet.app.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapperImpl implements CarMapper{

    @Autowired
    PersonService personService;

    @Override
    public Car dtoToEntity(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getModel(),
                carDto.getHorsepower(),
                personService.findById(carDto.getOwner()));
    }
}
