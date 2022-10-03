package com.krzysiekm266.homelibrary.person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> allPersons() {
        return this.personService.findAll();
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") Long personId) {
        return this.personService.findById(personId);
    }

    @GetMapping("/find")
    public ResponseEntity<Person> findPerson(@RequestBody Optional<Person> personToFind) {
        return this.personService.findPerson(personToFind);
    }

    @PostMapping("/create")
    public ResponseEntity<Person> create(@RequestBody Optional<Person> newPerson) {
        return this.personService.create(newPerson);
    }
   
}
