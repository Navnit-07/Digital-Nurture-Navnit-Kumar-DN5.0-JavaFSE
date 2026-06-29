package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Containing text
    List<Country> findByNameContaining(String name);

    // Containing text + sorting
    List<Country> findByNameContainingOrderByNameAsc(String name);

    // Starting with text
    List<Country> findByNameStartingWith(String prefix);
}
