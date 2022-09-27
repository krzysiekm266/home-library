package com.krzysiekm266.homelibrary.librarycard;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.krzysiekm266.homelibrary.book.Book;
import com.krzysiekm266.homelibrary.person.Person;

@Entity(name = "LibraryCard")
@Table(name = "library_card")
public class LibraryCard implements Serializable {
    @Id
    @SequenceGenerator(
        name = "library_card_sequence",
        sequenceName = "library_card_sequence",
        allocationSize = 1

    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "library_card_sequence"
    )
    private Long id;

    @SequenceGenerator(
        name = "library_id_sequence",
        sequenceName = "library_id_sequence",
        allocationSize = 1,
        initialValue = 100
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "library_id_sequence"
    )
    @Column(name = "library_id")
    private Integer libraryCardId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    
    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
		books.add(book);
		book.setLibraryCard(this);;
	}

	public void removeBook(Book book) {
		books.remove(book);
		book.setLibraryCard(null);;
	}

    public LibraryCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLibraryCardId() {
        return libraryCardId;
    }

    public void setLibraryCardId(Integer libraryCardId) {
        this.libraryCardId = libraryCardId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    
}
