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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.krzysiekm266.homelibrary.author.Author;
import com.krzysiekm266.homelibrary.librarycard.LibraryCard;

@Entity(name = "Book")
@Table(name = "book")
// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class, 
//   property = "id")
public class Book implements Serializable {
    @Id
    @SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(name="title", nullable = false,columnDefinition = "TEXT",unique = true)
    private String title;
    
   
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = { 
            
            CascadeType.PERSIST,
            CascadeType.MERGE,
             
        }
       
    )
    @JoinTable(
        name = "book_author",
        joinColumns =  @JoinColumn(name = "book_id") ,
        inverseJoinColumns =   @JoinColumn(name = "author_id") 
    )
    private Set<Author> authors = new HashSet<>();
    
    @ManyToOne
    private LibraryCard libraryCard;

    public Book addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
        return this;
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

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
    
    @Override
    public String toString() {
        return "Book [author=" + authors + ", id=" + id + ", title=" + title + "]";
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

   
    
    
    
}
