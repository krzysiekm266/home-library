package com.krzysiekm266.homelibrary.author;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.krzysiekm266.homelibrary.book.Book;

@Entity(name = "Author")
@Table(name = "author")
// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class, 
//   property = "id")

public class Author implements Serializable {
    @Id
    @SequenceGenerator(
        name = "author_sequence",
        sequenceName = "author_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "author_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(name="name",nullable = false,columnDefinition = "TEXT",unique = true)
    private String name;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();
   /***************do testow********************************* */
    public void addBook(Book book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }
    /**************************************** */
    public Author() {
    }
    
    public Author(String name) {
        this.name = name;
    }

    public Author(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author [Id=" + id + ", books=" + books + ", name=" + name + "]";
    }
    
   
        
}
