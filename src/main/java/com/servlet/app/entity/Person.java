package com.servlet.app.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
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

    @Past
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "Person_Birthdate", nullable = false)
    private Date birthdate;
}
