package com.krzysiekm266.homelibrary.author;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krzysiekm266.homelibrary.exceptions.AuthorNameExistException;
import com.krzysiekm266.homelibrary.exceptions.AuthorNameRequiredException;
import com.krzysiekm266.homelibrary.exceptions.AuthorNotFoundException;
import com.krzysiekm266.homelibrary.exceptions.AuthorRequiredException;

@Service
public class AuthorService {
    private  final AuthorRepository authorRepository;
  
    @Autowired
    public AuthorService(AuthorRepository authorRepository ) {
        this.authorRepository = authorRepository;
       
    }

    /**
     * Get all authors
     * */
    public  ResponseEntity<List<Author>> getAll() {
        List<Author> authors = this.authorRepository.findAll();
        return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
    }

    /**
     * Get author by Id
     * */
    public ResponseEntity<Author> get(Long authorId) {
        Author authorByid = this.authorRepository.findById(authorId)
            .orElseThrow( () ->  new AuthorNotFoundException("Author by Id: "+ authorId + "dosnt exist."));    
        return new ResponseEntity<Author>(authorByid,HttpStatus.OK);    
    }

    /**
     * Add new Author
     * */
    public ResponseEntity<Author> add(Author author) {
        Author newAuthor = null;
        String newAuthorName = null;
        if(author == null  ) {
            throw new AuthorRequiredException("Author required.");
        }
        else if(author.getName() == null || author.getName().isEmpty()) {
            throw new AuthorNameRequiredException("Author name required.");
        } 
        newAuthorName = author.getName();

        //Author check/create
        List<Author> authorByName =  authorRepository.findByName(newAuthorName);
        HttpStatus httpStatus  = authorByName.isEmpty() ? HttpStatus.CREATED : HttpStatus.FOUND;
        if(!authorByName.isEmpty()) {
            throw new AuthorNameExistException("Author already exist.");
        }
        newAuthor = new Author(newAuthorName);

        Author savedAuthor = this.authorRepository.save(newAuthor);
       
        return new ResponseEntity<Author>(savedAuthor,httpStatus);
    }

    /**
     * Update Author
     * */
    public ResponseEntity<Author> update(Long authorId,Author author) {
        Author authorById = this.authorRepository.findById(authorId)
            .orElseThrow( () ->  new AuthorNotFoundException("Author by Id: "+ authorId + "dosnt exist.") );
            
        if(Objects.equals(authorById.getName(), author.getName())) {
            throw new AuthorNameExistException("Author already exists.");
        }
        author.setId(authorId);
        authorById = author;
        Author authorUpdated = this.authorRepository.save(authorById);
        
        return new ResponseEntity<Author>(authorUpdated,HttpStatus.OK);
      
    }
}
