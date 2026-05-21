package org.vindhya.globallocationsinfo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Country information")
public class GenericCountryDto {

    @Schema(description = "Unique country identifier", example = "1")
    private Long id;

    @Schema(description = "Country name", example = "India")
    private String name;
}
