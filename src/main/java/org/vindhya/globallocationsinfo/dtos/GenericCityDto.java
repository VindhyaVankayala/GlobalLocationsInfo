package org.vindhya.globallocationsinfo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Detailed city information")
public class GenericCityDto {
    @Schema(description = "Unique city identifier", example = "1")
    private Long id;

    @Schema(description = "City name", example = "Mumbai")
    private String name;

    @Schema(description = "Identifier of the country this city belongs to", example = "1")
    private Long countryId;

    @Schema(description = "Short description of the city", example = "Financial capital of India")
    private String description;

    @Schema(description = "Country summary")
    private GenericCountryDto country;

    @Schema(description = "Approximate population", example = "20411274")
    private Long population;

    @Schema(description = "Representative postal or ZIP code", example = "400001")
    private String zipCode;

    @Schema(description = "Approximate current or representative temperature in Celsius", example = "32")
    private Integer temperature;
}
