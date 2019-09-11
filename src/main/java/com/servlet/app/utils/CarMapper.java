package com.servlet.app.utils;

import com.servlet.app.dto.CarDto;
import com.servlet.app.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car dtoToEntity(CarDto carDto);
}
