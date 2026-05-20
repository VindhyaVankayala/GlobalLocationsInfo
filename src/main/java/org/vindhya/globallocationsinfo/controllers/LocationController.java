package org.vindhya.globallocationsinfo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.vindhya.globallocationsinfo.dtos.PagedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vindhya.globallocationsinfo.dtos.CityDto;
import org.vindhya.globallocationsinfo.dtos.GenericCityDto;
import org.vindhya.globallocationsinfo.dtos.GenericCountryDto;
import org.vindhya.globallocationsinfo.services.LocationService;

import java.util.List;

@RestController
@RequestMapping("/locations")
@Tag(name = "Location Management", description = "Endpoints for querying global locations information")
public class LocationController {

    private final LocationService locationService;

    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/countries")
    @Operation(summary = "Get all countries", description = "Returns a list of all available countries")
    @ApiResponse(responseCode = "200", description = "Countries successfully retrieved")
    public ResponseEntity<List<GenericCountryDto>> getAllCountries() {
        return ResponseEntity.ok(locationService.getAllCountries());
    }

    @GetMapping("/countries/{countryId}/cities")
    @Operation(summary = "Get cities by country", description = "Returns a paginated list of cities belonging to the specified country")
    @ApiResponse(responseCode = "200", description = "Cities successfully retrieved")
    public ResponseEntity<PagedResponse<CityDto>> getAllCitiesByCountryId(
            @PathVariable Long countryId,
            @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(new PagedResponse<>(locationService.getCitiesByCountryId(countryId, pageable)));
    }

    @GetMapping("/cities/{cityId}")
    @Operation(summary = "Get city by ID", description = "Returns detailed information about a specific city")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City successfully found"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    public ResponseEntity<GenericCityDto> getCityDetailsByCityId(@PathVariable Long cityId) {
        return locationService.getCityById(cityId)
                .map(city -> ResponseEntity.ok(city))
                .orElse(ResponseEntity.notFound().build());
    }
}
