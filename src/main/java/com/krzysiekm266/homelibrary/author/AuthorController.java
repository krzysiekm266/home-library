package com.krzysiekm266.homelibrary.author;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAuthors() {
       return this.authorService.getAll();    
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable("authorId") Long authorId) {
        return this.authorService.get(authorId); 
    }
    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Optional<Author> author) {
        return this.authorService.add(author);       
    }

    @PutMapping("/update/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("authorId") Long authorId, @RequestBody Author author) {
         return this.authorService.update(authorId, author);
    }
}
