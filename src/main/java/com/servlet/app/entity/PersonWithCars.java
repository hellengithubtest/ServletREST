package com.servlet.app.entity;

import com.servlet.app.dto.CarDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class PersonWithCars implements Serializable {

    private Long id;
    private String name;
    private Date birthdate;

    private CarDto[] cars;
}
