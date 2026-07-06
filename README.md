# Cinema Booking Service

Microservice for user authentication, reservations, ticketing and payments.

Part of the CinemaTrack microservices architecture.

## Requirements

- Java 21
- Docker Desktop

## Running the Application

Start the database:

```bash
docker run --name cinema-booking-db \
  -e POSTGRES_PASSWORD=cinemabooking123 \
  -e POSTGRES_DB=cinemabookingdb \
  -e POSTGRES_USER=cinemabookinguser \
  -p 5435:5432 -d postgres:15
```

Run the application:

```bash
./mvnw spring-boot:run
```

API runs on `http://localhost:8082`

## Authentication

Register or login to get a JWT token. Add it to requests as `Authorization: Bearer <token>`.

All endpoints except `/api/auth/register` and `/api/auth/login` require authentication.

## API Documentation

Swagger UI available at `http://localhost:8082/swagger-ui/index.html`

## Architecture

Part of a microservices system:

- **cinema-core-service** — films, theaters, showtimes
- **cinema-booking-service** (this service) — reservations, tickets, payments
- **api-gateway** — routes requests to services

Publishes `bilet-satildi` events to Apache Kafka when a ticket is created.

## Tech Stack

- Java 21 (Temurin LTS)
- Spring Boot 3.5
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL 15
- Apache Kafka
- Docker
- Swagger / OpenAPI 3