package com.servlet.app.service;

import com.servlet.app.entity.Person;
import com.servlet.app.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    PersonRepository personRepository;


    public Long countPerson() {
        return personRepository.count();
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void clearPerson() {
        personRepository.deleteAll();
    }

    public Person findOne(Long personId) {
        return personRepository.findOne(personId);
    }
}
