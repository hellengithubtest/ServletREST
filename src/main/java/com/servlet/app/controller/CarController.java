package com.servlet.app.controller;


import com.servlet.app.dto.CarDto;
import com.servlet.app.services.CarService;
import com.servlet.app.utils.CarMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    @NonNull
    private CarService carService;
    @NonNull
    private CarMapper carMapper;


    @GetMapping(value = "/clear")
    public ResponseEntity<Void> getClear() {
        carService.clearCar();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> saveCar(@Valid @RequestBody CarDto carDto, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            List<String> message = new ArrayList<>();

            for (FieldError e : errors) {
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            return new ResponseEntity<String>(message.toString(), HttpStatus.BAD_REQUEST);

        } else {

            carService.saveCar(carMapper.dtoToEntity(carDto));
            return new ResponseEntity<String>("The Car is successfully saved", HttpStatus.OK);
        }
    }
}
