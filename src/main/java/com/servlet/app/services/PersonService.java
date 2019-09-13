package com.servlet.app.services;

import com.servlet.app.dto.PersonWithCars;
import com.servlet.app.entity.Person;
import com.servlet.app.mapper.PersonMapper;
import com.servlet.app.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Validated
@Service
@RequiredArgsConstructor
public class PersonService {
    @NonNull
    private PersonRepository personRepository;

    @NonNull
    private PersonMapper personMapper;

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

    @Transactional(rollbackFor = Exception.class)
    public void clearPerson() {
        personRepository.deleteAll();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Person findById(Long personId) {
        return personRepository.findById(personId).orElseThrow(() ->
                new EntityNotFoundException("Not found Person with this Id"));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PersonWithCars findPersonWithCars(Long personId) {
        return personMapper.entityToDto(personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Not found Person with this Id")));
    }
}
