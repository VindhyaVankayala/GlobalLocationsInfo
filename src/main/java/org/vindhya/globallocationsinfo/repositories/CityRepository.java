package org.vindhya.globallocationsinfo.repositories;

import org.vindhya.globallocationsinfo.models.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    List<City> findAll();
    Optional<City> findById(Long id);
    List<City> findAllByCountryId(Long countryId);
}
