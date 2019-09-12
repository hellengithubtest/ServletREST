package com.servlet.app.services;

import com.servlet.app.entity.Person;
import com.servlet.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Validated
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long countPerson() {
        return personRepository.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public void savePerson( @Valid  Person person) {
        personRepository.findById(person.getId()).ifPresent(e -> {
            throw new EntityExistsException("The person is exit");
        });
        personRepository.save(person);
    }

    @Transactional(rollbackFor = Exception.class)
    public void clearPerson() {
        personRepository.deleteAll();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Person findById(Long personId) {
        return personRepository.findById(personId).orElseThrow(() ->
            new EntityNotFoundException("Not found Person with this Id"));
    }
}
