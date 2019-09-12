package com.servlet.app.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        if (entityValue == null) {
            return null;
        }
        System.out.println("1");
        return Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (databaseValue == null) {
            return null;
        }

        System.out.println("2" + databaseValue);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate local = LocalDate.parse(databaseValue.toLocalDate(), formatter);
        //System.out.println("Local" + local);
        return databaseValue.toLocalDate();
    }
}
