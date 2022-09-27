package com.krzysiekm266.homelibrary.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
 
   @Query("select a from Author a ")
   List<Author> findAllByAuthor();
   List<Author> findByName(String name);
}
