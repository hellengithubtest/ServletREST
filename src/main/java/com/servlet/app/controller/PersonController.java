package com.servlet.app.controller;

import com.servlet.app.dto.CarDto;
import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;

import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.entity.Statistics;
import com.servlet.app.services.CarService;
import com.servlet.app.services.PersonService;
import com.servlet.app.services.PersonWithCarsService;
import com.servlet.app.services.StatisticsService;
import com.servlet.app.utils.Adulthood;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    @NonNull
    private PersonService personService;
    @NonNull
    private PersonWithCarsService personWithCarsService;

    @GetMapping(value = "/clear")
    public ResponseEntity<Void> getClear() {
        personService.clearPerson();
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/personwithcars/{id}")
    public ResponseEntity<PersonWithCars> getPerson(@Valid @PathVariable(value = "id") Long id) {
/*
        if (result.hasErrors()){
            System.out.println("has error");
        }
*/
        PersonWithCars personWithCars = personWithCarsService.getPersonWithCars(id);

        if (personWithCars == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personWithCars);
        }
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity savePerson(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        } else {
            personService.savePerson(person);
            return ResponseEntity.ok().build();
        }
    }



}
