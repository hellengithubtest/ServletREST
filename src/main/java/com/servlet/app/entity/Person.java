package com.servlet.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = "Person_Id")})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    @PositiveOrZero
    @Column(name = "Person_Id", nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(name = "Person_Name", nullable = false)
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    //@DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "Person_Birthdate", nullable = false)
    private Date birthdate;
}
