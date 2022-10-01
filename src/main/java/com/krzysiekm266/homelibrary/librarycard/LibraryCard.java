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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;
    
    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
		books.add(book);
		book.setLibraryCard(this);
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

    @Override
    public String toString() {
        return "LibraryCard [books=" + books + ", id=" + id + ", libraryCardId=" + libraryCardId + ", person=" + person
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((books == null) ? 0 : books.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((libraryCardId == null) ? 0 : libraryCardId.hashCode());
        result = prime * result + ((person == null) ? 0 : person.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LibraryCard other = (LibraryCard) obj;
        if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (libraryCardId == null) {
            if (other.libraryCardId != null)
                return false;
        } else if (!libraryCardId.equals(other.libraryCardId))
            return false;
        if (person == null) {
            if (other.person != null)
                return false;
        } else if (!person.equals(other.person))
            return false;
        return true;
    }

    

    
}
