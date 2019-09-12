package com.servlet.app.controller;

import com.servlet.app.entity.Person;
import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.services.PersonService;
import com.servlet.app.services.PersonWithCarsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PersonController {

    @NonNull
    private PersonService personService;
    @NonNull
    private PersonWithCarsService personWithCarsService;

    @GetMapping(value = "/personwithcars/{id}")
    public ResponseEntity<PersonWithCars> getPerson(@Valid @PathVariable(value = "id") Long id) {
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
