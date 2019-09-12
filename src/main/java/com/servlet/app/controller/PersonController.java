package com.servlet.app.controller;

import com.servlet.app.entity.Person;
import com.servlet.app.dto.PersonWithCars;
import com.servlet.app.services.PersonService;
import com.servlet.app.services.PersonWithCarsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
public class PersonController {

    @NonNull
    private PersonService personService;
    @NonNull
    private PersonWithCarsService personWithCarsService;

    @GetMapping(value = "/personwithcars")
    public ResponseEntity<PersonWithCars> getPerson(@Valid @RequestParam(value = "personid") Long personid) {
        if (personWithCarsService.getPersonWithCars(personid) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personWithCarsService.getPersonWithCars(personid));
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
