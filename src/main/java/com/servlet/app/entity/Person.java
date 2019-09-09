package com.servlet.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Table(name = "person")
@Entity
public class Person {
    @Id
    @Column(name = "Person_Id", nullable = false)
    private Long id;
    @Column(name = "Person_Name", nullable = false)
    private String name;

    @Column(name = "Person_Birthdate", nullable = false)
    private Date birthdate;
}
