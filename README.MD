# Bank API - ITS Steve Jobs Academy

This repository contains a Spring Boot banking API application developed during the course at the ITS Steve Jobs Academy. The application provides a RESTful API for managing bank accounts with various operations.

## Project Overview

The Bank API is a Java-based backend application that allows users to:

- Create bank accounts
- Retrieve account information
- Search accounts by owner name
- Filter accounts by account type
- Delete accounts

## Repository Structure

The project follows a standard Spring Boot application structure:

- **controllers/**: REST API endpoints
  - `ContoController.java`: Handles HTTP requests for bank account operations

- **models/**: Data models
  - `ContoBancario.java`: Represents a bank account entity with properties like ID, owner, balance, and account type

- **repositories/**: Data access layer
  - `ContoRepository.java`: JPA repository interface for database operations

- **providers/**: Service layer
  - `ContoService.java`: Contains business logic for account operations with validation

## API Endpoints

The application exposes the following REST endpoints:

- **GET /conti**: Retrieve all bank accounts
- **POST /conti**: Create a new bank account
- **DELETE /conti/{id}**: Delete a bank account by ID
- **GET /conti/ricerca?nome={name}**: Search accounts by owner name
- **GET /conti/tipo?tipo={type}**: Filter accounts by account type

## Data Model

The `ContoBancario` (Bank Account) entity includes:

- `id`: Unique identifier (auto-generated)
- `intestatario`: Account owner's name
- `saldo`: Account balance
- `tipoConto`: Account type

## Business Rules

The application enforces several validation rules:

- Account owner name cannot be null or empty
- Account type cannot be null or empty
- Account balance cannot be negative
- Duplicate accounts for the same owner are not allowed

## Technologies Used

- **Java**: Core programming language
- **Spring Boot**: Application framework
- **Spring Data JPA**: Data persistence
- **PostgreSQL**: Database
- **Hibernate ORM**: Object-relational mapping
- **Maven**: Dependency management

## Database Configuration

The application is configured to connect to a PostgreSQL database hosted on Neon Tech. Connection details are specified in the `application.properties` file.

## How to Run

To run the application locally:

1. Ensure you have Java 17+ and Maven installed
2. Clone the repository
3. Navigate to the project directory
4. Run the application using Maven:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Usage Examples

### Create a new account
```bash
curl -X POST http://localhost:8080/conti -H "Content-Type: application/json" -d '{"intestatario":"Mario Rossi","saldo":1000.0,"tipoConto":"Corrente"}'
```

### Get all accounts
```bash
curl -X GET http://localhost:8080/conti
```

### Search accounts by owner name
```bash
curl -X GET "http://localhost:8080/conti/ricerca?nome=Mario"
```

### Filter accounts by type
```bash
curl -X GET "http://localhost:8080/conti/tipo?tipo=Corrente"
```

### Delete an account
```bash
curl -X DELETE http://localhost:8080/conti/1
```

## Educational Objectives

This project was designed to:

- Demonstrate Spring Boot application development
- Implement RESTful API design principles
- Apply data validation and error handling
- Practice database integration with JPA
- Develop service-oriented architecture

---

*This repository was created as part of the educational journey at ITS Steve Jobs Academy.*