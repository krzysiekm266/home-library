package com.krzysiekm266.homelibrary.person;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.krzysiekm266.homelibrary.exceptions.person.PersonExistException;
import com.krzysiekm266.homelibrary.exceptions.person.PersonNotFoundException;
import com.krzysiekm266.homelibrary.exceptions.person.PersonRequiredException;



@Service
public class PersonService {
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity<List<Person>> findAll() {
        List<Person> findAll = this.personRepository.findAll();
        return new ResponseEntity<List<Person>>(findAll,HttpStatus.OK);
    }

    public ResponseEntity<Person> findById(Long personId) {
        Person findById = this.personRepository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException("Person not found."));
        return new ResponseEntity<Person>(findById,HttpStatus.FOUND);
    }
/*************************************************************************** */
    public ResponseEntity<Person> findPerson(@RequestBody Optional<Person> personToFind) {
        Person person = personToFind.orElseThrow( () -> new PersonRequiredException("Person required.") );
        Person findPerson = this.personRepository.findPerson(person)
            .orElseThrow( () -> new PersonNotFoundException("Person not found."));
        return new ResponseEntity<Person>(findPerson,HttpStatus.FOUND);
    }
/******************************************* */
    public ResponseEntity<Person> create(Optional<Person> person) {
        Person newPerson = person.orElseThrow(() -> new PersonRequiredException("Person required."));
       
        if(newPerson.getFirstName() == null) {
            throw new PersonRequiredException("First name field is required.");
        }
        if(newPerson.getLastName() == null) {
            throw new PersonRequiredException("Last name field is required.");
        }
        if(newPerson.getGender() == null) {
            throw new PersonRequiredException("Gender field is required.");
        }
    
        if(newPerson.getDob() == null) {
            throw new PersonRequiredException("Date of birth field is required.");
        }
        if(newPerson.getAddress() == null) {
            throw new PersonRequiredException("First name field is required.");
        }

        Example<Person> personExample = Example.of(newPerson); 
        Boolean findByExampleExist = this.personRepository.findOne(personExample).isPresent();
        if(findByExampleExist) {
            throw new PersonExistException("Person already exists.");
        }
        Person createdPerson =  this.personRepository.save(newPerson);
        return new ResponseEntity<Person>(createdPerson,HttpStatus.OK);
    }

   
    
}
