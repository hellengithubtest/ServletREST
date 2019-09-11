package com.servlet.app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handleNotFound(final RuntimeException ex) {
        //System.out.println(ex.getMessage());
        /*List<FieldError> errors = result.getFieldErrors();*/
/*        List<String> message = new ArrayList<>();
        for (FieldError e : errors) {
         message.add("Field " + e.getField().toUpperCase() + " - " + e.getDefaultMessage());
         }*/
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
