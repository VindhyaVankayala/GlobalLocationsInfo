package org.vindhya.globallocationsinfo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vindhya.globallocationsinfo.dtos.CityDto;
import org.vindhya.globallocationsinfo.dtos.GenericCityDto;
import org.vindhya.globallocationsinfo.dtos.GenericCountryDto;
import org.vindhya.globallocationsinfo.exceptions.ResourceNotFoundException;
import org.vindhya.globallocationsinfo.models.City;
import org.vindhya.globallocationsinfo.models.Country;
import org.vindhya.globallocationsinfo.repositories.CityRepository;
import org.vindhya.globallocationsinfo.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

	private final CountryRepository countryRepository;
	private final CityRepository cityRepository;

	public LocationService(CountryRepository countryRepository, CityRepository cityRepository) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
	}

	public List<GenericCountryDto> getAllCountries() {
		return countryRepository.findAll().stream()
				.map(country -> toCountryDto(country))
				.toList();
	}

	public Page<CityDto> getCitiesByCountryId(Long countryId, Pageable pageable) {
		countryRepository.findById(countryId)
				.orElseThrow(() -> new ResourceNotFoundException("Country not found with id " + countryId));

		List<CityDto> allCities = cityRepository.findAllByCountryId(countryId).stream()
				.map(city -> toCityDto(city))
				.toList();

		int total = allCities.size();
		int start = pageable.getOffset() > total ? total : (int) pageable.getOffset();
		int end = Math.min(start + pageable.getPageSize(), total);

		List<CityDto> pageContent = allCities.subList(start, end);

		return new PageImpl<>(pageContent, pageable, total);
	}

	public Optional<GenericCityDto> getCityById(Long cityId) {
		return cityRepository.findById(cityId).map(city -> toCityDetailDto(city));
	}

	private GenericCountryDto toCountryDto(Country country) {
		GenericCountryDto dto = new GenericCountryDto();
		dto.setId(country.getId());
		dto.setName(country.getName());
		return dto;
	}

	private CityDto toCityDto(City city) {
		CityDto dto = new CityDto();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setCountryId(city.getCountry().getId());
		return dto;
	}

	private GenericCityDto toCityDetailDto(City city) {
		GenericCityDto dto = new GenericCityDto();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setCountryId(city.getCountry().getId());
		dto.setDescription(city.getDescription());
		dto.setPopulation(city.getPopulation());
		dto.setTemperature(city.getTemperature());
		dto.setCountry(toCountryDto(city.getCountry()));
		return dto;
	}
}
