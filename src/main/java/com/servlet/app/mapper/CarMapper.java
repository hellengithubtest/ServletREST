package com.servlet.app.mapper;

import com.servlet.app.dto.CarDto;
import com.servlet.app.entity.Car;
import com.servlet.app.repository.PersonRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;

@Mapper(componentModel = "spring")
public abstract class CarMapper {

    @Autowired
    private PersonRepository personRepository;

    public Car dtoToEntity(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getModel(),
                carDto.getHorsepower(),
                personRepository.findById(carDto.getOwnerId()).orElseThrow(() -> new EntityExistsException("Entity not found with current ownerId")));
    }

    @Mappings({
            @Mapping(target = "ownerId", source="ownerId")
    })
    abstract CarDto entityToDto(Car car);
}