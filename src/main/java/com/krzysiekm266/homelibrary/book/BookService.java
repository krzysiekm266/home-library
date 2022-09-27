package com.krzysiekm266.homelibrary.book;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krzysiekm266.homelibrary.author.Author;
import com.krzysiekm266.homelibrary.author.AuthorRepository;
import com.krzysiekm266.homelibrary.exceptions.author.AuthorRequiredException;
import com.krzysiekm266.homelibrary.exceptions.book.BookNotFoundException;
import com.krzysiekm266.homelibrary.exceptions.book.BookRequiredException;
import com.krzysiekm266.homelibrary.exceptions.book.BookTitleExistException;
import com.krzysiekm266.homelibrary.exceptions.book.BookTitleRequiredException;

@Service
public class BookService {
    private  final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
  
    @Autowired
    public BookService(BookRepository bookRepository,AuthorRepository authorRepository ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
       
    }

    /**
     * Get all books 
     * */
    public  ResponseEntity<List<Book>> getAll() {
        List<Book> books = this.bookRepository.findAll();
        return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
    }

    /**
     * Get book by Id 
     * */
    public ResponseEntity<Book> get(Long bookId) {
        Book bookByid = this.bookRepository.findById(bookId)
            .orElseThrow( () ->  new BookNotFoundException("Book by Id: "+ bookId + "dosnt exist."));    
        return new ResponseEntity<Book>(bookByid,HttpStatus.OK);    
    }

    /**
     * Add new book 
     * */
    public ResponseEntity<Book> add(Book book) {

        Author newAuthor = null;
        Book newBook = null;

        if(book == null) {
            throw new BookRequiredException("Book required");
        }
        if(book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new BookTitleRequiredException("Book Title required.");
        }
        if(book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new AuthorRequiredException("Author is required.");
        }
        //Book check
        List<Book> bookByTitle = this.bookRepository.findByTitle(book.getTitle());
        newBook = bookByTitle.isEmpty() ? new Book(book.getTitle()) : bookByTitle.get(0);
        //Author check/create
        String bookAuthorName = book.getAuthor().iterator().next().getName();
        List<Author> authorByName = authorRepository.findByName(bookAuthorName);
        newAuthor = authorByName.isEmpty() ? new Author(bookAuthorName) : authorByName.get(0);
      
        newBook.addAuthor(newAuthor);  
        Book savedNewBook = this.bookRepository.save(newBook);
      
        return new ResponseEntity<Book>(savedNewBook,HttpStatus.CREATED);
    }

    /**
     * Update book 
     * */
    public ResponseEntity<Book> update(Long bookId,Book book) {
        Book bookById = this.bookRepository.findById(bookId)
            .orElseThrow( () ->  new BookNotFoundException("Book by Id: "+ bookId + "dosnt exist.") );
            
        if(Objects.equals(bookById.getTitle(), book.getTitle())) {
            throw new BookTitleExistException("Title already exists.");
        }
        book.setId(bookId);
        bookById = book;
        Book bookUpdated = this.bookRepository.save(bookById);
        
        return new ResponseEntity<Book>(bookUpdated,HttpStatus.OK);
      
    }
}
