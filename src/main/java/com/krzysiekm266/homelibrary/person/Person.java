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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    
    @Column(name = "first_name",columnDefinition = "TEXT")
    private String firstName ;

    @Column(name = "last_name",columnDefinition = "TEXT")
    private String lastName ;

    @Transient
   // @Column(name = "name",columnDefinition = "TEXT")
    private String name;
 
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Transient
    @Column(name = "age")
    private Integer age;

    @Column(name = "address",columnDefinition = "TEXT")
    private String address;


    @JsonIgnore
    @OneToOne(
        cascade = CascadeType.ALL, 
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "library_card_id")
    private LibraryCard libraryCard;

    
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
        this.name = String.join(" ", this.firstName,this.lastName);
        return this.name;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    @Override
    public String toString() {
        return "Person [address=" + address + ", age=" + age + ", dob=" + dob + ", firstName=" + firstName + ", id="
                + id + ", lastName=" + lastName + ", libraryCard=" + libraryCard + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((libraryCard == null) ? 0 : libraryCard.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Person other = (Person) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (gender != other.gender)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (libraryCard == null) {
            if (other.libraryCard != null)
                return false;
        } else if (!libraryCard.equals(other.libraryCard))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    
  
   
}
