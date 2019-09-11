package com.servlet.app.services;

import com.servlet.app.entity.Person;
import com.servlet.app.entity.PersonWithCars;
import com.servlet.app.repository.CarRepository;
import com.servlet.app.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class PersonWithCarsService {

    @NonNull
    PersonService personService;
    @NonNull
    CarService carService;

    public PersonWithCars getPersonWithCars(Long id) {
        System.out.println("get Person with cars");
        PersonWithCars personWithCars = new PersonWithCars();
        System.out.println("get Person with cars1");
        Person person = personService.findOne(id);
        System.out.println("get Person with cars2");
        System.out.println(person);

        person.getBirthdate();
        personWithCars.setCars(carService.findByOwnerId(id));

        personWithCars.setId(person.getId());
        personWithCars.setName(person.getName());
        personWithCars.setBirthdate(person.getBirthdate());
        return personWithCars;
    }


}
