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
            .orElseThrow( () ->  new BookNotFoundException("Book by Id: "+ bookId + " dosen't exist."));    
        return new ResponseEntity<Book>(bookByid,HttpStatus.OK);    
    }

    /**
     * Add new book 
     * */
    public ResponseEntity<Book> add(Optional<Book> book) {

        Author createAuthor = null;
        Book createBook = null;
        Book newBook = book.orElseThrow( () -> new BookRequiredException("Book required"));
        // if(book == null) {
        //     throw new BookRequiredException("Book required");
        // }
        if(newBook.getTitle() == null || newBook.getTitle().isEmpty()) {
            throw new BookTitleRequiredException("Book Title required.");
        }
        if(newBook.getAuthors() == null || newBook.getAuthors().isEmpty()) {
            throw new AuthorRequiredException("Author is required.");
        }
        //Book check
        List<Book> bookByTitle = this.bookRepository.findByTitle(newBook.getTitle());
        if(!bookByTitle.isEmpty()) {
            String title =  bookByTitle.iterator().next().getTitle(); 
            throw new BookTitleExistException("Book title " + title +" already exist");
        }
        createBook = new Book(newBook.getTitle()); 
        //Author check/create
        String bookAuthorName = newBook.getAuthors().iterator().next().getName();
        List<Author> authorByName = authorRepository.findByName(bookAuthorName);
        createAuthor = authorByName.isEmpty() ? new Author(bookAuthorName) : authorByName.get(0);
      
        createBook.addAuthor(createAuthor);  
        Book savedNewBook = this.bookRepository.save(createBook);
      
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
        bookById.setTitle(book.getTitle());;
        this.bookRepository.flush();
       // Book bookUpdated = this.bookRepository.save(bookById);
        
        return new ResponseEntity<Book>(bookById,HttpStatus.OK);
      
    }
}
