package com.servlet.app.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class PersonWithCars implements Serializable {

    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
/*    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Convert(converter = LocalDateConverter.class)*/
    private LocalDate birthdate;

    private CarDto[] cars;
}
