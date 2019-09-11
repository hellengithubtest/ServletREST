package com.servlet.app.services;

import com.servlet.app.entity.Person;
import com.servlet.app.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PersonService {

    PersonRepository personRepository;


    public Long countPerson() {
        return personRepository.count();
    }

    @Transactional
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
