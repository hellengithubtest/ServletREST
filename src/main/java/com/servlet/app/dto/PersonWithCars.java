package com.servlet.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servlet.app.utils.LocalDateConverter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


@Data
public class PersonWithCars implements Serializable {

    private Long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthdate;

    @NotEmpty
    private CarDto[] cars;
}
