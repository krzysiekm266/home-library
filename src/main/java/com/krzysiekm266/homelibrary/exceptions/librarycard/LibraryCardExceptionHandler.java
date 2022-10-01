package com.krzysiekm266.homelibrary.exceptions.librarycard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibraryCardExceptionHandler {

    @ExceptionHandler(value = {LibraryCardNotFoundException.class})
    public ResponseEntity<Object> handleLibraryCardNotFoundException(LibraryCardNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        LibraryCardNotFoundException libraryCardNotFoundException = new LibraryCardNotFoundException( "Library card not found.");
        return new ResponseEntity<Object>(libraryCardNotFoundException,httpStatus);

    }

    @ExceptionHandler(value = {LibraryCardRequiredException.class})
    public ResponseEntity<Object> handleLibraryCardRequiredException(LibraryCardRequiredException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        LibraryCardRequiredException libraryCardRequiredException = new LibraryCardRequiredException( "Library card not found.");
        return new ResponseEntity<Object>(libraryCardRequiredException,httpStatus);

    }
}
