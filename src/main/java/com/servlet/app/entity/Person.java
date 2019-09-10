package com.servlet.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "person")
@NoArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    @NonNull
    @Column(name = "Person_Id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "Person_Name", nullable = false)
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "Person_Birthdate", nullable = false)
    private Date birthdate;
}
