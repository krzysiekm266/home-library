package com.krzysiekm266.homelibrary.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.krzysiekm266.homelibrary.librarycard.LibraryCard;

@Entity(name = "Person")
@Table(name = "person")
public class Person implements Serializable{
    @Id
    @SequenceGenerator(
        name = "person_sequence",
        sequenceName = "person_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "person_sequence")
    private Long id;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    @Column(name = "name")
    private String name;
 
    @Column(name = "date_of_birth")
    LocalDate dob;

    @Transient
    @Column(name = "age")
    Integer age;

    @Column(name = "address")
    private String address;

    @OneToOne(
        mappedBy = "person",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private LibraryCard libraryCard;

    public void addLibraryCard(LibraryCard libraryCard) {
		libraryCard.setPerson(this);;
		this.libraryCard = libraryCard;
	}

	public void removeLibraryCard() {
		if (libraryCard != null) {
			libraryCard.setPerson(null);;
			this.libraryCard = null;
		}
	}
    
    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate dob, String address, LibraryCard libraryCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.libraryCard = libraryCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        name = String.join(" ", this.firstName,this.lastName);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    
}
