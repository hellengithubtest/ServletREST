package com.servlet.app.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.servlet.app.utils.LocalDateSerializer;
import com.servlet.app.utils.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PersonWithCars implements Serializable {

    private Long id;
    private String name;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
/*    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Convert(converter = LocalDateConverter.class)*/
    //@DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthdate;

    private CarDto[] cars;
}
