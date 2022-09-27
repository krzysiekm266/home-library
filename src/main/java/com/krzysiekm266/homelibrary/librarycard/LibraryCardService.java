package com.krzysiekm266.homelibrary.librarycard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;

    @Autowired
    public LibraryCardService(LibraryCardRepository libraryCardRepository) {
        this.libraryCardRepository = libraryCardRepository;
    }
    
}
