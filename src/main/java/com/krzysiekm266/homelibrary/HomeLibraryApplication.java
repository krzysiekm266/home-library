package com.krzysiekm266.homelibrary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.krzysiekm266.homelibrary.author.Author;
import com.krzysiekm266.homelibrary.author.AuthorRepository;
import com.krzysiekm266.homelibrary.book.Book;
import com.krzysiekm266.homelibrary.book.BookRepository;

@SpringBootApplication
public class HomeLibraryApplication implements CommandLineRunner {
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;

	

	public static void main(String[] args) {
		SpringApplication.run(HomeLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book b1 = new Book("Potop");
		Book b2 = new Book("Art of war");
		Author a1 = new Author("Henryk Sienkiewicz");
		Author a2 = new Author("Sun Zi");
		Author a3 = new Author("Adam Mickiewicz");
		this.bookRepository.saveAll(List.of(b1, b2));
		this.authorRepository.saveAll(List.of(a1, a2,a3));
		/********************************************************** */
		b1.addAuthor(a1);
		b1.addAuthor(a3);
		b2.addAuthor(a2);
		b2.addAuthor(a3);
		/************************************************************* */
		// b1.getAuthor().add(this.authorRepository.findById(1L).get());
		// b1.getAuthor().add(this.authorRepository.findById(3L).get());
		// b2.getAuthor().add(this.authorRepository.findById(2L).get());
		// b2.getAuthor().add(this.authorRepository.findById(3L).get());
		/************************************************************ */
		this.bookRepository.saveAll(List.of(b1, b2));
		
		
	}

}
