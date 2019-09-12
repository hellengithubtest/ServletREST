package com.servlet.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servlet.app.utils.LocalDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.convert.threetenbp.ThreeTenBackPortJpaConverters;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    //@DateTimeFormat(pattern = "dd.MM.yyyy")
    @Convert(converter = LocalDateConverter.class)
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
}
