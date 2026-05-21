package org.vindhya.globallocationsinfo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.vindhya.globallocationsinfo.dtos.PagedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vindhya.globallocationsinfo.dtos.CityDto;
import org.vindhya.globallocationsinfo.dtos.GenericCityDto;
import org.vindhya.globallocationsinfo.dtos.GenericCountryDto;
import org.vindhya.globallocationsinfo.services.LocationService;

import java.util.List;

@RestController
@Tag(name = "Locations", description = "APIs for countries and their cities")
public class LocationController {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;

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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cities successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found", content = @Content)
    })
    public ResponseEntity<PagedResponse<CityDto>> getAllCitiesByCountryId(
            @Parameter(description = "Country identifier", example = "1")
            @PathVariable Long countryId,
            @Parameter(description = "Zero-based page index", example = "0")
            @RequestParam(defaultValue = "" + DEFAULT_PAGE) int page,
            @Parameter(description = "Number of records per page. Maximum value is 100.", example = "10")
            @RequestParam(defaultValue = "" + DEFAULT_SIZE) int size) {
        validatePagination(page, size);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new PagedResponse<>(locationService.getCitiesByCountryId(countryId, pageable)));
    }

    @GetMapping("/cities/{cityId}")
    @Operation(summary = "Get city by ID", description = "Returns detailed information about a specific city")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "City successfully found",
                    content = @Content(schema = @Schema(implementation = GenericCityDto.class))),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content)
    })
    public ResponseEntity<GenericCityDto> getCityDetailsByCityId(
            @Parameter(description = "City identifier", example = "1")
            @PathVariable Long cityId) {
        return locationService.getCityById(cityId)
                .map(city -> ResponseEntity.ok(city))
                .orElse(ResponseEntity.notFound().build());
    }

    private void validatePagination(int page, int size) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page must be greater than or equal to 0");
        }

        if (size < 1 || size > MAX_PAGE_SIZE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "size must be between 1 and " + MAX_PAGE_SIZE);
        }
    }
}
