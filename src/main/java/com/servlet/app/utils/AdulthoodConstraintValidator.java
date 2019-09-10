package com.servlet.app.utils;

import com.servlet.app.entity.Person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AdulthoodConstraintValidator implements ConstraintValidator<Adulthood, Person> {
    @Override
    public boolean isValid(Person person, ConstraintValidatorContext constraintValidatorContext) {
        Date date = person.getBirthdate();
        if(18 > ChronoUnit.YEARS.between(LocalDate.now(), LocalDate.of(date.getYear(), date.getMonth(), date.getDay()))) {
            return false;
        } else {
            return true;
        }
    }
}
