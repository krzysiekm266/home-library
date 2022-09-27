package com.krzysiekm266.homelibrary.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
}
