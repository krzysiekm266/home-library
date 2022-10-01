package com.krzysiekm266.homelibrary.exceptions.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler {

    @ExceptionHandler(value = {PersonExistException.class})
    public ResponseEntity<Object> handlePersonExistException(PersonExistException e) {
        HttpStatus httpStatus = HttpStatus.FOUND;
        PersonExistException personExistException = new PersonExistException(e.getMessage());
        return new ResponseEntity<Object>(personExistException.getLocalizedMessage(),httpStatus);
    }

    @ExceptionHandler(value = {PersonRequiredException.class})
    public ResponseEntity<Object> handlePersonRequiredException(PersonRequiredException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        PersonRequiredException personRequiredException = new PersonRequiredException(e.getMessage());
        return new ResponseEntity<Object>(personRequiredException.getLocalizedMessage(),httpStatus);
    }

    @ExceptionHandler(value = {PersonNotFoundException.class})
    public ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        PersonNotFoundException personNotFoundException = new PersonNotFoundException(e.getMessage());
        return new ResponseEntity<Object>(personNotFoundException.getLocalizedMessage(),httpStatus);
    }
}
