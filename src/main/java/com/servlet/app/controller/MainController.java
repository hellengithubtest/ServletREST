package com.servlet.app.controller;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;

import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.entity.Statistics;
import com.servlet.app.services.CarService;
import com.servlet.app.services.PersonService;
import com.servlet.app.services.PersonWithCarsService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    @NonNull
    private CarService carService;
    @NonNull
    private PersonService personService;
    @NonNull
    private PersonWithCarsService personWithCarsService;


    @GetMapping(value = "/statistics")
    public ResponseEntity<Void> getStatistics() {
        Statistics statistics = new Statistics();
        statistics.setPersoncount(personService.countPerson());
        statistics.setCarcount(carService.countCar());
        statistics.setUniquevendorcount(carService.countModelDistinct());
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/clear")
    public ResponseEntity<Void> getClear() {
        carService.clearCar();
        personService.clearPerson();
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/personwithcars/{id}")
    public ResponseEntity<PersonWithCars> getPerson(@PathVariable(value = "id") Long id) {

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
            throw new IllegalArgumentException();
        } else {
            personService.savePerson(person);
            return ResponseEntity.ok().build();
        }
/*        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            List<String> message = new ArrayList<>();
            System.out.println("Has error");
            for (FieldError e : errors) {
                message.add("Field " + e.getField().toUpperCase() + " - " + e.getDefaultMessage());
            }
                return ResponseEntity.badRequest().build();
        } else {
            personService.savePerson(person);
            return ResponseEntity.ok().build();
        }*/
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> saveCar(@Valid @RequestBody Car car, BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            List<String> message = new ArrayList<>();

            for (FieldError e : errors) {
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            return new ResponseEntity<String>(message.toString(), HttpStatus.BAD_REQUEST);

        } else {
            carService.saveCar(car);
            return new ResponseEntity<String>("The Car is successfully saved", HttpStatus.OK);
        }
    }

}
