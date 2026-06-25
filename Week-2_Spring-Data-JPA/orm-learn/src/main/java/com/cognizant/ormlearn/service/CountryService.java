package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Find a country based on country code
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);

        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }

        Country country = result.get();
        return country;
    }

    // Add a new country
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Update country name based on code
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Country country = findCountryByCode(code);
        country.setName(name);
        countryRepository.save(country);
    }

    // Delete country based on code
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Find countries matching partial country name
    @Transactional
    public List<Country> findByPartialName(String name) {
        return countryRepository.findByNameContaining(name);
    }
}
