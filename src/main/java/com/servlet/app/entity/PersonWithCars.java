package com.servlet.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
public class PersonWithCars implements Serializable {
    private Long id;
    private String name;
    private Date birthdate;

    private Car[] cars;
}
