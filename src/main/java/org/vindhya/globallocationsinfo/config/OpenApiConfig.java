package org.vindhya.globallocationsinfo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI globalLocationsOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Global Locations API")
                        .version("v1")
                        .description("Backend APIs for listing countries, listing cities by country with pagination, and retrieving city details."));
    }
}
