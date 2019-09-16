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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CarRepository carRepository;

    @Test
    public void shouldFindNoCarsIfRepositoryIsEmpty() {
        List<Car> personIterable = carRepository.findAll();
        assertThat(personIterable).isEmpty();
    }
}
