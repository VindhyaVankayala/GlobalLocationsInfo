package org.vindhya.globallocationsinfo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Summary information for a city")
public class CityDto {

    @Schema(description = "Unique city identifier", example = "1")
    private Long id;

    @Schema(description = "City name", example = "Mumbai")
    private String name;

    @Schema(description = "Identifier of the country this city belongs to", example = "1")
    private Long countryId;
}
