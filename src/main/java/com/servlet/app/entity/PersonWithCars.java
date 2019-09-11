package com.servlet.app.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class PersonWithCars implements Serializable {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    private Date birthdate;

    private Car[] cars;
}
