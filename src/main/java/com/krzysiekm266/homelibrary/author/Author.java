package com.krzysiekm266.homelibrary.author;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table
public class Author implements Serializable {
    @Id
    @SequenceGenerator(
        name = "author_sequence",
        sequenceName = "author_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "author_sequence")
    private Long Id;

    @Column(name="name",nullable = false)
    private String name;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors",
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        }
    )
    private Set<Book> books = new HashSet<>();
   

    public Author() {
    }
    
    public Author(String name) {
        this.name = name;
    }

    public Author(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Author(Long id, String name, Set<Book> books) {
        this.Id = id;
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long id) {
        this.Id = id;
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
        return "Author [Id=" + Id + ", books=" + books + ", name=" + name + "]";
    }
    
   
        
}
