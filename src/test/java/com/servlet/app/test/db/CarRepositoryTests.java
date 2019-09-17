package com.servlet.app.test.db;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import com.servlet.app.repository.CarRepository;
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
public class CarRepositoryTests {
    private Person person = new Person(200L, "nameRepository", LocalDate.of(1992, 10, 10), null);
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CarRepository carRepository;

    @Test
    public void shouldFindNoCarsIfRepositoryIsEmpty() {
        List<Car> personIterable = carRepository.findAll();
        assertThat(personIterable).isEmpty();
    }

    @Test
    public void shouldStoreACar() {
        System.out.println(carRepository.findAll());
        Car car = carRepository.save(new Car(200L, "BMW-X5", 100, person));
        assertThat(car).hasFieldOrPropertyWithValue("id", 200L);
        assertThat(car).hasFieldOrPropertyWithValue("model", "BMW-X5");
        //assertThat(person).hasFieldOrPropertyWithValue("birthdate", new Date.valueOf("1992.10.10")); /TODO
    }

    @Test
    public void shouldDeleteAllCars() {
        entityManager.persist(new Car(210L, "BMW-X5", 100, person));
        entityManager.persist(new Car(211L, "BMW-X5", 110, person));

        carRepository.deleteAll();
        assertThat(carRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldFindAllCars() {
        Car car1 =  new Car(220L, "BMW-X5", 100, person);
        entityManager.persist(car1);

        Car car2 =  new Car(221L, "BMW-X5", 110, person);
        entityManager.persist(car2);

        Car car3 =  new Car(222L, "BMW-X5", 120, person);
        entityManager.persist(car3);

        Iterable<Car> carIterable = carRepository.findAll();
        assertThat(carIterable).hasSize(3).contains(car1, car2, car3);
    }

    @Test
    public void shouldFindCarById() {
        Car car1 =  new Car(230L, "BMW-X5", 100, person);
        entityManager.persist(car1);

        Car car2 =  new Car(231L, "BMW-X5", 100, person);
        entityManager.persist(car2);

        Car foundCar = carRepository.findById(car2.getId()).get();
        assertThat(foundCar).isEqualTo(car2);
    }
}
