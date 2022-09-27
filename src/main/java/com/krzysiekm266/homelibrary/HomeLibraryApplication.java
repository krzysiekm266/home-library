package com.krzysiekm266.homelibrary;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
		Author a1 = new Author("Henryk Sienkiewicz");
		Author a2 = new Author("Sun Zi");
		Author a3 = new Author("Adam Mickiewicz");

		Book b1 = new Book("Potop");
		Book b2 = new Book("Art of war");
		
	
		/********************************************************** */

		b1.addAuthor(a1);
		b1.addAuthor(a3);
		b2.addAuthor(a2);
		b2.addAuthor(a3);
		
		/************************************************************* */
		

		this.bookRepository.saveAll(List.of(b1, b2));
		this.authorRepository.saveAll(List.of(a1, a2,a3));

		
		
		
	}
	
	//Cors config 
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
