package org.vindhya.globallocationsinfo.repositories;

import org.vindhya.globallocationsinfo.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    List<Country> findAll();
    Optional<Country> findById(Long id);
}
