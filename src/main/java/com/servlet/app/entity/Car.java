package com.servlet.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servlet.app.utils.Adulthood;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car implements Serializable {
    @Id
    @Column(name = "Car_Id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "Car_Model", length = 30, nullable = false)
    private String model;

    @NotNull
    @Positive
    @Column(name = "Car_Horsepower", nullable = false)
    private Integer horsepower;

    @NotNull
    @Adulthood
    @JoinColumn(name = "Car_Ownerid", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Person owner;



}
