package com.krzysiekm266.homelibrary.author;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krzysiekm266.homelibrary.exceptions.author.AuthorNameExistException;
import com.krzysiekm266.homelibrary.exceptions.author.AuthorNameRequiredException;
import com.krzysiekm266.homelibrary.exceptions.author.AuthorNotFoundException;
import com.krzysiekm266.homelibrary.exceptions.author.AuthorRequiredException;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getBooks() {
       return this.authorService.getAll();
        
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getBook(@PathVariable("authorId") Long authorId) {
        try {
            return this.authorService.get(authorId);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Optional<Author> author) {
        try {
            return this.authorService.add(author);   
        } catch (AuthorRequiredException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (AuthorNameRequiredException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (AuthorNameExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.IM_USED);
        }
         
    }

    @PutMapping("/update/{authorId}")
    public ResponseEntity<?> updateBook(@PathVariable("authorId") Long authorId, @RequestBody Author author) {
        try {
            return this.authorService.update(authorId, author);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (AuthorNameExistException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }     
    }
}
