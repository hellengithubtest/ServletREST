package com.servlet.app.repository;

import com.servlet.app.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car [] findByOwner(Long owner);

    @Query("select count(DISTINCT c.model) from Car c ")
    Long countByUniqueName();
}
