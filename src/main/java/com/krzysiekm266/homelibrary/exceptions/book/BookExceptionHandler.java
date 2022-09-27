package com.krzysiekm266.homelibrary.exceptions.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class BookExceptionHandler {
    
    @ExceptionHandler(value = {BookNotFoundException.class})
    ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        BookNotFoundException bookNotFoundException = new BookNotFoundException(e.getMessage());
        return new ResponseEntity<>(bookNotFoundException.getLocalizedMessage(),httpStatus);

    }

    @ExceptionHandler(value = {BookRequiredException.class})
    ResponseEntity<Object> handleBookRequiredException(BookRequiredException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        BookRequiredException bookRequiredException = new BookRequiredException(e.getMessage());
        return new ResponseEntity<>(bookRequiredException.getLocalizedMessage(),httpStatus);

    }

    @ExceptionHandler(value = {BookTitleRequiredException.class})
    ResponseEntity<Object> handleBookTitleRequiredException(BookTitleRequiredException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        BookTitleRequiredException bookTitleRequiredException = new BookTitleRequiredException(e.getMessage());
        return new ResponseEntity<>(bookTitleRequiredException.getLocalizedMessage(),httpStatus);

    }

    @ExceptionHandler(value = {BookTitleExistException.class})
    ResponseEntity<Object> handleBookTitleExistException(BookTitleExistException e) {
        HttpStatus httpStatus = HttpStatus.IM_USED;
        BookTitleExistException bookTitleExistException = new BookTitleExistException(e.getMessage());
        return new ResponseEntity<>(bookTitleExistException.getLocalizedMessage(),httpStatus);

    }
}
