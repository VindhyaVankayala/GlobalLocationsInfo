package org.vindhya.globallocationsinfo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericCityDto {
    private Long id;
    private String name;
    private String description;
    private GenericCountryDto country;
    private Long population;
    private Integer temperature;
}
