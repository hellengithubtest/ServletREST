package com.servlet.app.utils;

import com.servlet.app.entity.Person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class AdulthoodConstraintValidator implements ConstraintValidator<Adulthood, Person> {
    @Override
    public boolean isValid(Person person, ConstraintValidatorContext constraintValidatorContext) {
        Date date = person.getBirthdate();
        Calendar today = Calendar.getInstance();
        Calendar birthdate = Calendar.getInstance();
        birthdate.setTime(date);
        return  (today.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR))>18 ? true : false;
    }
}
