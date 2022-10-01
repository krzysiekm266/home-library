package com.krzysiekm266.homelibrary.librarycard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krzysiekm266.homelibrary.person.Person;


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

    @PostMapping("/create")
    public ResponseEntity<LibraryCard> createLibraryCard(@RequestBody Optional<Person> person) {
        return this.libraryCardService.createLibraryCard(person);
    }

    @PutMapping("/update/{cardId}")
    public ResponseEntity<LibraryCard> updateLibraryCard(@PathVariable("cardId") Long cardId, @RequestBody Optional<LibraryCard> newLibraryCardData) {
        return this.libraryCardService.updateLibraryCard(cardId, newLibraryCardData);
    }

    @DeleteMapping("/delete/{cardId}")
    public ResponseEntity<Long> deleteLibraryCard(@PathVariable("cardId") Long cardId) {
        return this.libraryCardService.deleteLibraryCardById(cardId);
    } 
}
