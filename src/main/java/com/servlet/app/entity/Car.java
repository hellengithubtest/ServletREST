package com.servlet.app.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @Column(name = "Car_Id", nullable = false)
    private Long id;
    @Column(name = "Car_Model", length = 30, nullable = false)
    private String model;
    @NonNull
    @Column(name = "Car_Horsepower", nullable = false)
    private Integer horsepower;
    @Column(name = "Car_Ownerid", length = 30, nullable = false)
    private Long ownerId;
}
