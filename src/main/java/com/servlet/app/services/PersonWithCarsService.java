package com.servlet.app.services;

import com.servlet.app.entity.Person;
import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.repository.CarRepository;
import com.servlet.app.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonWithCarsService {

    @Autowired
    PersonService personService;
    @Autowired
    CarService carService;

    public PersonWithCars getPersonWithCars(Long id) {
        PersonWithCars personWithCars = new PersonWithCars();
        Person person = personService.findById(id);
        personWithCars.setCars(carService.findByOwner(id));
        personWithCars.setId(person.getId());
        personWithCars.setName(person.getName());
        personWithCars.setBirthdate(person.getBirthdate());
        return personWithCars;
    }


}
