package com.servlet.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    @PositiveOrZero(message = "Id - must be positive or zero")
    @Column(name = "Person_Id", nullable = false)
    private Long id;

    @NotBlank(message = "Name - must be not blank")
    @Column(name = "Person_Name", nullable = false)
    private String name;

    @Past(message = "Birthdate - must be in past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    //@DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "Person_Birthdate", nullable = false)
    private Date birthdate;
}
