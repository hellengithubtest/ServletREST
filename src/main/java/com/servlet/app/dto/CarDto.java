package com.servlet.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CarDto implements Serializable {
    @NotNull
    @PositiveOrZero
    private Long id;

    @NotBlank
    private String model;

    @NotNull
    @Positive
    private Integer horsepower;

    @NotNull
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Long owner;

}
