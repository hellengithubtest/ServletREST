package com.servlet.app.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @Column(name = "Car_Id", nullable = false)
    private Long id;

    @Column(name = "Car_Model", length = 30, nullable = false)
    private String model;

    @NotNull
    @Positive
    @Column(name = "Car_Horsepower", nullable = false)
    private Integer horsepower;

    @Column(name = "Car_Ownerid", length = 30, nullable = false)
    private Long ownerId;
}
