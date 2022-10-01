package com.krzysiekm266.homelibrary.librarycard;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krzysiekm266.homelibrary.book.Book;
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
    /**********************get all library cards*********************************************** */
    public ResponseEntity<List<LibraryCard>> findAll() { 
        List<LibraryCard> findAll = this.libraryCardRepository.findAll();
        return new ResponseEntity<List<LibraryCard>>(findAll,HttpStatus.OK);
    }
    /*****************************find card******************************************* */
    public ResponseEntity<LibraryCard> findLibraryCardById(Long cardId) {
        LibraryCard findById = this.libraryCardRepository.findById(cardId)
            .orElseThrow( () -> new LibraryCardNotFoundException("Library card with id: @{`cardId`} not found"));
        return new ResponseEntity<LibraryCard>(findById,HttpStatus.OK);
    }

    /*********************************create card****************************************** */
    public ResponseEntity<LibraryCard> createLibraryCard( Optional<Person> person) {
       
        Person cardOwner = person.orElseThrow( () -> new PersonRequiredException("Person required exception."));
        
         //Person findPerson = this.personRepository.findPerson(cardOwner).orElseThrow( () -> new PersonExistException("Person already exist."));
         
        // if(findPerson.isPresent()) {
        //     throw new PersonExistException("Person "+ findPerson.get().getName() +" already exist.");
        // }
        
        LibraryCard newLibraryCard = this.libraryCardRepository.save(new LibraryCard());
        cardOwner.addLibraryCard(newLibraryCard);
        newLibraryCard.setPerson(cardOwner);

        this.personRepository.save(cardOwner);
        LibraryCard savedLibraryCard = this.libraryCardRepository.save(newLibraryCard);

        return new ResponseEntity<LibraryCard>(savedLibraryCard,HttpStatus.CREATED);
    }

    /********************************update card**************************** */
    public ResponseEntity<LibraryCard> updateLibraryCard(Long cardId, Optional<LibraryCard> newLibraryCardData) {
        LibraryCard findById = this.libraryCardRepository.findById(cardId)
            .orElseThrow( () -> new LibraryCardNotFoundException("Library card not found") );
        if( newLibraryCardData.isPresent()) {
            if(newLibraryCardData.get().getBooks() == null || newLibraryCardData.get().getBooks().isEmpty() ) {
               throw new LibraryCardNotFoundException("Library card books not found.");
            }
            if(newLibraryCardData.get().getPerson() == null ) {
                throw new LibraryCardNotFoundException("Library card person not found.");
            } 
        }

        Set<Book> books = newLibraryCardData.get().getBooks();   
        Person person = newLibraryCardData.get().getPerson();
        findById.setPerson(person);
        findById.setBooks(books);
        this.libraryCardRepository.flush();

        return new ResponseEntity<LibraryCard>(findById,HttpStatus.OK);
    }
    /***************************delete card**************************************************** */
    public ResponseEntity<Long> deleteLibraryCardById(Long cardId) {
        boolean existsById = this.libraryCardRepository.existsById(cardId);
        if(!existsById) {
            throw new LibraryCardNotFoundException("Library card with id: @{`cardId`} not found");
        }
        this.libraryCardRepository.deleteById(cardId);
        return new ResponseEntity<Long>(cardId,HttpStatus.OK);
    }

    

    
}
