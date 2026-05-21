package org.vindhya.globallocationsinfo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Long id;
    private String name;
    private Country country;
    private String description;
    private Long population;
    private String zipCode;
    private Integer temperature;
}
