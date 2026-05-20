# GlobalLocationsInfo

GlobalLocationsInfo is a Spring Boot REST API that serves basic country and city information from an in-memory dataset.

## Features

- List available countries
- List cities for a country with pagination
- Get detailed information for a city
- Explore the API with Swagger UI / OpenAPI

## Tech Stack

- Java 21
- Spring Boot 4
- Maven Wrapper
- springdoc OpenAPI

## Prerequisites

- Java 21

## Run Locally

```bash
./mvnw spring-boot:run
```

The application starts on the default Spring Boot port:

- API base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Run Tests

```bash
./mvnw test
```

## API Endpoints

### Get all countries

```http
GET /locations/countries
```

Example response:

```json
[
  { "id": 1, "name": "India" },
  { "id": 2, "name": "United States" }
]
```

### Get cities by country

```http
GET /locations/countries/{countryId}/cities?page=0&size=5
```

Example response:

```json
{
  "content": [
    { "id": 1, "name": "Mumbai" },
    { "id": 2, "name": "Delhi" }
  ],
  "page": 0,
  "size": 5,
  "totalElements": 2,
  "totalPages": 1,
  "first": true,
  "last": true
}
```

### Get city details

```http
GET /locations/cities/{cityId}
```

Example response:

```json
{
  "id": 1,
  "name": "Mumbai",
  "description": "Financial capital of India",
  "country": {
    "id": 1,
    "name": "India"
  },
  "population": 20411274,
  "temperature": 32
}
```

## Sample Data

The application currently exposes an in-memory dataset with:

- 6 countries
- 15 cities

No database setup is required.
