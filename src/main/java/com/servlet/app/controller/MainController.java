package com.servlet.app.controller;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.entity.Statistics;
import com.servlet.app.service.CarService;
import com.servlet.app.service.PersonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainController {
    @NonNull
    private CarService carService;
    @NonNull
    private PersonService personService;

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
        PersonWithCars personWithCars = new PersonWithCars();

        personWithCars.setCars(carService.findByOwnerId(id));
        Person person = personService.findOne(id);

        personWithCars.setId(person.getId());
        personWithCars.setName(person.getName());
        personWithCars.setBirthdate(person.getBirthdate());

        if (personWithCars == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personWithCars);
        }
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> savePerson(@RequestBody Person person) {
        personService.savePerson(person);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> saveCar(@RequestBody Car car) {
        carService.saveCar(car);
        return ResponseEntity.ok().build();
    }

}
