package com.servlet.app.services;

import com.servlet.app.entity.Person;
import com.servlet.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;


    public Long countPerson() {
        return personRepository.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public void savePerson(Person person) {
        personRepository.findById(person.getId()).ifPresent(e -> {
            throw new EntityExistsException("The person is exit");
        });
        personRepository.save(person);
    }

    public void clearPerson() {
        personRepository.deleteAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Person findById(Long personId) {
        return personRepository.findById(personId).orElseThrow(() ->
            new EntityNotFoundException("Not found Person with this Id"));
    }
}
