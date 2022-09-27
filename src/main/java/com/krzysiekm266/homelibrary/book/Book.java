package com.krzysiekm266.homelibrary.book;

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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.krzysiekm266.homelibrary.author.Author;

@Entity
@Table
public class Book implements Serializable {
    @Id
    @SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_sequence")
    private Long id;

    @Column(name="title", nullable = false)
    private String title;
    
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        }
    )
    @JoinTable(
        name = "book_author",
        joinColumns =  @JoinColumn(name = "book_id") ,
        inverseJoinColumns =   @JoinColumn(name = "author_id") 
    )
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }
    
    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, Set<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Book(Long id, String title, Set<Author> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthor() {
        return this.authors;
    }

    public void setAuthor(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book [author=" + authors + ", id=" + id + ", title=" + title + "]";
    }

   
    
    
    
}
