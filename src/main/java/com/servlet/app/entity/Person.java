package com.servlet.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {
    @NotNull
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Past
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private List<Car> cars;
}
