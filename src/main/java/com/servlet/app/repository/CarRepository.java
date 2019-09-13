package com.servlet.app.repository;

import com.servlet.app.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByOwnerId(Long owner);

    @Query(nativeQuery = true, value = "select count(distinct UPPER(CAST((subquery.regexp_matches[1]) AS varchar))) from (select regexp_matches(c.model, '^(\\w+)([\\\\-])([a-zA-Z0-9\\\\-]+)$', 'g') from Car c) as subquery;")
    Long countByUniqueName();
}
