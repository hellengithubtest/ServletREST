package com.servlet.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto implements Serializable {
    @NotNull
    private Long id;

    @NotNull
    @Pattern(regexp = "^(\\w+)([\\\\-])([a-zA-Z0-9\\\\-]+)$", message = "Format vendor-model")
    private String model;

    @NotNull
    @Positive
    private Integer horsepower;

    @NotNull
    private Long ownerId;

}
