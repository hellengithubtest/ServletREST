package com.servlet.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class PersonWithCars {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date birthdate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn()
    private Car [] cars;
}
