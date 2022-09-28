package com.krzysiekm266.homelibrary.librarycard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/library-card")
public class LibraryCardController {
    private LibraryCardService libraryCardService;
    
    @Autowired
    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LibraryCard>> allLibraryCards() {
        return this.libraryCardService.findAll();
    }
}
