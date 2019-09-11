package com.servlet.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servlet.app.entity.Car;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;


public class CarDto implements Serializable {
    private Long id;

    @NotBlank(message = "Model - is not blank")
    private String model;

    @NotNull(message = "Horsepower -")
    @Positive(message = "Horsepower - must be positive")
    private Integer horsepower;

    @NotNull
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Long owner;

/*    public Car _toConvertCarEntity() {
        Car car = new Car();
        car.setId(id);
        car.setModel(model);
        car.setHorsepower(horsepower);
        car.setOwner();
    }*/
}
