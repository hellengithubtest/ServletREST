package com.servlet.app.mapper;

import com.servlet.app.dto.PersonWithCars;
import com.servlet.app.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface PersonMapper {

    PersonWithCars entityToDto(Person person);

}
