package com.krzysiekm266.homelibrary.exceptions.book;

public class BookTitleExistException extends RuntimeException{

    public BookTitleExistException(String message) {
        super(message);
    }
    
}
