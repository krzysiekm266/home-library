package com.krzysiekm266.homelibrary.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId) {
        return this.bookService.get(bookId);
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {   
        return this.bookService.add(book);   
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
            return this.bookService.update(bookId, book); 
    }
}
