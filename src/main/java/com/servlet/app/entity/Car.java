package com.servlet.app.entity;

import com.servlet.app.utils.Adulthood;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "car")
@NoArgsConstructor
@Data
public class Car implements Serializable {
    @Id
    @Column(name = "Car_Id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "Car_Model", length = 30, nullable = false)
    private String model;

    @NotNull
    @Positive
    @Column(name = "Car_Horsepower", nullable = false)
    private Integer horsepower;

    @NonNull
    @Adulthood
    @JoinColumn(name = "ownerId", referencedColumnName = "Person_Id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Person ownerId;
}
