package org.vindhya.globallocationsinfo.repositories;

import org.springframework.stereotype.Repository;
import org.vindhya.globallocationsinfo.models.City;
import org.vindhya.globallocationsinfo.models.Country;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryCityRepository implements CityRepository {

	private final Map<Long, City> cities = new LinkedHashMap<>();

	public InMemoryCityRepository(CountryRepository countryRepository) {
		Country india = countryRepository.findById(1L).orElseThrow();
		Country us = countryRepository.findById(2L).orElseThrow();
		Country uk = countryRepository.findById(3L).orElseThrow();
		Country canada = countryRepository.findById(4L).orElseThrow();
		Country australia = countryRepository.findById(5L).orElseThrow();
		Country france = countryRepository.findById(6L).orElseThrow();

		cities.put(1L, new City(1L, "Mumbai", india, "Financial capital of India", 20_411_274L, "400001", 32));
		cities.put(2L, new City(2L, "Delhi", india, "Capital city of India", 32_941_000L, "110001", 31));
		cities.put(3L, new City(3L, "New York", us, "Largest city in the US", 8_804_190L, "10001", 22));
		cities.put(4L, new City(4L, "Los Angeles", us, "Entertainment capital", 3_898_747L, "90001", 25));
		cities.put(5L, new City(5L, "London", uk, "Capital of the UK", 8_961_989L, "SW1A 1AA", 19));
		cities.put(6L, new City(6L, "Manchester", uk, "Major city in the UK", 2_782_831L, "M1 1AE", 17));
		cities.put(7L, new City(7L, "Toronto", canada, "Largest city in Canada", 2_794_356L, "M5H 2N2", 18));
		cities.put(8L, new City(8L, "Vancouver", canada, "West coast city in Canada", 662_248L, "V6B 1A1", 16));
		cities.put(9L, new City(9L, "Sydney", australia, "Largest city in Australia", 5_312_163L, "2000", 21));
		cities.put(10L, new City(10L, "Melbourne", australia, "Cultural capital of Australia", 5_159_211L, "3000", 20));
		cities.put(11L, new City(11L, "Paris", france, "Capital and largest city of France", 2_161_000L, "75001", 15));
		cities.put(12L, new City(12L, "Marseille", france, "Second largest city of France", 869_815L, "13001", 18));
		cities.put(13L, new City(13L, "Lyon", france, "Third largest city in France", 513_485L, "69001", 14));
		cities.put(14L, new City(14L, "Toulouse", france, "Fourth largest city in France", 479_553L, "31000", 16));
		cities.put(15L, new City(15L, "Nice", france, "Popular coastal city on French Riviera", 340_017L, "06000", 17));
	}

	@Override
	public List<City> findAll() {
		return List.copyOf(cities.values());
	}

	@Override
	public Optional<City> findById(Long id) {
		return Optional.ofNullable(cities.get(id));
	}

	@Override
	public List<City> findAllByCountryId(Long countryId) {
		return cities.values().stream()
				.filter(city -> city.getCountry().getId().equals(countryId))
				.toList();
	}
}
