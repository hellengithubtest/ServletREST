package com.servlet.app.repository;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByOwner(Person owner);

    @Query("select count(DISTINCT c.model) from Car c ")
    Long countByUniqueName();

    Optional<Car> findById(Long id);
}
