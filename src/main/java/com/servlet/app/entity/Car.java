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

    public Car(Long id, @NotNull String model,
               @NotNull @Positive Integer horsepower,
               @NotNull Person owner) {
        this.id = id;
        this.model = model;
        this.horsepower = horsepower;
        this.owner = owner;
    }
    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "model", length = 30, nullable = false)
    private String model;

    @NotNull
    @Positive
    @Column(name = "horsepower", nullable = false)
    private Integer horsepower;

    @Adulthood
    @NotNull
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Person owner;

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;

}
