package com.krzysiekm266.homelibrary.exceptions.author;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthorExceptionHandler {

    @ExceptionHandler(value = {AuthorNameExistException.class})
    ResponseEntity<Object> handleAuthorNameExistException(AuthorNameExistException e) {
        HttpStatus httpStatus = HttpStatus.IM_USED;
        AuthorNameExistException authorNameExistException = new AuthorNameExistException(e.getMessage());
        return new ResponseEntity<>(authorNameExistException.getLocalizedMessage(),httpStatus);
    }

    @ExceptionHandler(value = {AuthorNameRequiredException.class})
    ResponseEntity<Object> handleAuthorNameRequiredException(AuthorNameRequiredException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        AuthorNameRequiredException authorNameRequiredException = new AuthorNameRequiredException(e.getMessage());
        return new ResponseEntity<>(authorNameRequiredException.getLocalizedMessage(),httpStatus);
    }

    @ExceptionHandler(value = {AuthorNotFoundException.class})
    ResponseEntity<Object> handleAuthorNotFoundException(AuthorNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        AuthorNotFoundException authorNotFoundException = new AuthorNotFoundException(e.getMessage());
        return new ResponseEntity<>(authorNotFoundException.getLocalizedMessage(),httpStatus);
    }

    @ExceptionHandler(value = {AuthorRequiredException.class})
    ResponseEntity<Object> handleAuthorRequiredException(AuthorRequiredException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        AuthorRequiredException authorRequiredException = new AuthorRequiredException(e.getMessage());
        return new ResponseEntity<>(authorRequiredException.getLocalizedMessage(),httpStatus);
    }
}
