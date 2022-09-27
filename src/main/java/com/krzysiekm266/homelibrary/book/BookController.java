package com.krzysiekm266.homelibrary.book;

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

import com.krzysiekm266.homelibrary.exceptions.author.AuthorRequiredException;
import com.krzysiekm266.homelibrary.exceptions.book.BookNotFoundException;
import com.krzysiekm266.homelibrary.exceptions.book.BookTitleExistException;
import com.krzysiekm266.homelibrary.exceptions.book.BookTitleRequiredException;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks() {
       return this.bookService.getAll();
        
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable("bookId") Long bookId) {
        try {
            return this.bookService.get(bookId);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            return this.bookService.add(book);  
        } catch (BookTitleRequiredException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NO_CONTENT);
        } catch (AuthorRequiredException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NO_CONTENT);
        } 
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        try {
            return this.bookService.update(bookId, book);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (BookTitleExistException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.IM_USED);
        }     
    }
}
