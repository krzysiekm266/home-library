package com.krzysiekm266.homelibrary.librarycard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Long> {
    
}
