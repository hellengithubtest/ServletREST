package com.servlet.app.test.db;


import com.servlet.app.entity.Person;
import com.servlet.app.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void shouldFindNoPersonsIfRepositoryIsEmpty() {
        List<Person> personIterable = personRepository.findAll();

        assertThat(personIterable).isEmpty();
    }

    @Test
    public void shouldStoreAPerson() {
        System.out.println(personRepository.findAll());
        Person person = personRepository.save(new Person(200L, "nameRepository", LocalDate.of(1992, 10, 10), null));
        assertThat(person).hasFieldOrPropertyWithValue("id", 200L);
        assertThat(person).hasFieldOrPropertyWithValue("name", "nameRepository");
        assertThat(person).hasFieldOrPropertyWithValue("birthdate", "10.10.1992");
    }

    @Test
    public void shouldDeleteAllPerson() {
        entityManager.persist(new Person(201L, "nameRepository1", LocalDate.of(1992, 10, 10), null));
        entityManager.persist(new Person(202L, "nameRepository2", LocalDate.of(1992, 10, 10), null));

        personRepository.deleteAll();
        assertThat(personRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldFindAllPersons() {
        Person person1 =  new Person(211L, "nameRepository1", LocalDate.of(1992, 10, 10), null);
        entityManager.persist(person1);

        Person person2 =  new Person(212L, "nameRepository1", LocalDate.of(1992, 10, 10), null);
        entityManager.persist(person2);

        Person person3 =  new Person(213L, "nameRepository1", LocalDate.of(1992, 10, 10), null);
        entityManager.persist(person3);

        Iterable<Person> personIterable = personRepository.findAll();
        assertThat(personIterable).hasSize(3).contains(person1, person2, person3);
    }

    @Test
    public void shouldFindPersonById() {
        Person person1 =  new Person(221L, "nameRepository1", LocalDate.of(1992, 10, 10), null);
        entityManager.persist(person1);

        Person person2 =  new Person(222L, "nameRepository1", LocalDate.of(1992, 10, 10), null);
        entityManager.persist(person2);

        Person foundPerson = personRepository.findById(person2.getId()).get();
        assertThat(foundPerson).isEqualTo(person2);
    }
}
