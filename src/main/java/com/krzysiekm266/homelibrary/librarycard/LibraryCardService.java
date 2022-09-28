package com.krzysiekm266.homelibrary.librarycard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krzysiekm266.homelibrary.exceptions.librarycard.LibraryCardNotFoundException;
import com.krzysiekm266.homelibrary.exceptions.librarycard.LibraryCardRequiredException;
import com.krzysiekm266.homelibrary.exceptions.person.PersonExistException;
import com.krzysiekm266.homelibrary.exceptions.person.PersonRequiredException;
import com.krzysiekm266.homelibrary.person.Person;
import com.krzysiekm266.homelibrary.person.PersonRepository;

@Service
public class LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;
    private final PersonRepository personRepository;
   
    @Autowired
    public LibraryCardService(LibraryCardRepository libraryCardRepository,PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.libraryCardRepository = libraryCardRepository;
    }
    
    public ResponseEntity<List<LibraryCard>> findAll() { 
        List<LibraryCard> findAll = this.libraryCardRepository.findAll();
        return new ResponseEntity<List<LibraryCard>>(findAll,HttpStatus.OK);
    }

    public ResponseEntity<LibraryCard> findLibraryCardById(Long cardId) {
        LibraryCard findById = this.libraryCardRepository.findById(cardId)
            .orElseThrow( () -> new LibraryCardNotFoundException("Library card with id: @{`cardId`} not found"));
        return new ResponseEntity<LibraryCard>(findById,HttpStatus.OK);
    }

    public ResponseEntity<LibraryCard> saveLibraryCard( Optional<Person> person) {
    
        Person cardOwner = person.orElseThrow( () -> new PersonRequiredException("Person required exception."));
        Optional<Person> findPerson = this.personRepository.findPerson(cardOwner);
        if(findPerson.isPresent()) {
            throw new PersonExistException("Person "+ findPerson.get().getName() +" already exist.");
        }
        LibraryCard newLibraryCard = new LibraryCard();
        newLibraryCard.setPerson(cardOwner);
        LibraryCard savedLibraryCard = this.libraryCardRepository.save(newLibraryCard);

        return new ResponseEntity<LibraryCard>(savedLibraryCard,HttpStatus.CREATED);
    }

    public ResponseEntity<Long> deleteLibraryCardById(Long cardId) {
        boolean existsById = this.libraryCardRepository.existsById(cardId);
        if(!existsById) {
            throw new LibraryCardNotFoundException("Library card with id: @{`cardId`} not found");
        }
        this.libraryCardRepository.deleteById(cardId);
        return new ResponseEntity<Long>(cardId,HttpStatus.OK);
    }

    

    
}
