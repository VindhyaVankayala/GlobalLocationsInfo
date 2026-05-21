# Global Locations Info

Simple Spring Boot backend service for countries and cities. The service uses in-memory data so it can run without database setup.

## Requirements Covered

- `GET /countries` returns the available countries.
- `GET /countries/{countryId}/cities?page=0&size=10` returns paginated cities for the selected country.
- `GET /cities/{cityId}` returns detailed city information by id.
- Swagger UI is available when the app runs.
- The generated OpenAPI JSON is available when the app runs.

## Run Locally

```bash
./mvnw spring-boot:run
```

Application:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/api-docs
```

## Example Requests

```bash
curl http://localhost:8080/countries
curl "http://localhost:8080/countries/6/cities?page=0&size=2"
curl http://localhost:8080/cities/1
```

## Test

```bash
./mvnw test
```

## Notes

- Pagination is zero-based: `page=0` returns the first page.
- `size` must be between `1` and `100`.
- Unknown countries and cities return `404`.
