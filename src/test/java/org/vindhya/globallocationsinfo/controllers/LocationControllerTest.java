package org.vindhya.globallocationsinfo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCountriesReturnsCountries() throws Exception {
        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("India"));
    }

    @Test
    void getCitiesByCountryReturnsPagedCities() throws Exception {
        mockMvc.perform(get("/countries/{countryId}/cities", 6L)
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name").value("Paris"))
                .andExpect(jsonPath("$.content[0].countryId").value(6))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.totalElements").value(5))
                .andExpect(jsonPath("$.totalPages").value(3))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(false));
    }

    @Test
    void getCitiesByCountryReturnsNotFoundForUnknownCountry() throws Exception {
        mockMvc.perform(get("/countries/{countryId}/cities", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource not found"))
                .andExpect(jsonPath("$.detail").value("Country not found with id 999"));
    }

    @Test
    void getCitiesByCountryRejectsInvalidPagination() throws Exception {
        mockMvc.perform(get("/countries/{countryId}/cities", 1L)
                        .param("page", "-1")
                        .param("size", "10"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/countries/{countryId}/cities", 1L)
                        .param("page", "0")
                        .param("size", "101"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCityDetailsReturnsCityById() throws Exception {
        mockMvc.perform(get("/cities/{cityId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mumbai"))
                .andExpect(jsonPath("$.countryId").value(1))
                .andExpect(jsonPath("$.zipCode").value("400001"))
                .andExpect(jsonPath("$.country.name").value("India"));
    }

    @Test
    void getCityDetailsReturnsNotFoundForUnknownCity() throws Exception {
        mockMvc.perform(get("/cities/{cityId}", 999L))
                .andExpect(status().isNotFound());
    }
}
