package org.vindhya.globallocationsinfo.repositories;

import org.springframework.stereotype.Repository;
import org.vindhya.globallocationsinfo.models.Country;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCountryRepository implements CountryRepository {

	private final Map<Long, Country> countries = new LinkedHashMap<>();

	public InMemoryCountryRepository() {
		countries.put(1L, new Country(1L, "India"));
		countries.put(2L, new Country(2L, "United States"));
		countries.put(3L, new Country(3L, "United Kingdom"));
		countries.put(4L, new Country(4L, "Canada"));
		countries.put(5L, new Country(5L, "Australia"));
		countries.put(6L, new Country(6L, "France"));
	}

	@Override
	public List<Country> findAll() {
		return countries.values().stream().toList();
	}

	@Override
	public Optional<Country> findById(Long id) {
		return Optional.ofNullable(countries.get(id));
	}
}
