package com.servlet.app.controller;

import com.servlet.app.entity.Person;
import com.servlet.app.dto.PersonWithCars;
import com.servlet.app.services.PersonService;
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

    @GetMapping(value = "/personwithcars")
    public ResponseEntity<PersonWithCars> getPerson(@Valid @RequestParam(value = "personid") Long personid) {
        return ResponseEntity.ok(personService.findPersonWithCars(personid));
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
