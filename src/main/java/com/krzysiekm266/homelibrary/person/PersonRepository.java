package com.krzysiekm266.homelibrary.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
     @Query("select p from Person p where p =:person")
     public Optional<Person> findPerson(Person person);
}
