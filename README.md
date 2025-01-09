# SpaceX Rockets API Project

This project is a Spring Boot application that provides a simple API to interact with the SpaceX API, fetching information about SpaceX rockets. The project utilizes Spring WebClient from the `org.springframework.web.reactive` package for making HTTP requests to the SpaceX API. The main challenge of the project was maintaining a structured representation of the large data model provided by the SpaceX API.

---

## Features

- Fetch details about SpaceX rockets via SpaceX's public API.
- Leverages reactive programming with `WebClient` for efficient API calls.
- Designed with a clean structure to handle the complex data model of the SpaceX API.

---

## Requirements

- Java 17
- Maven 3.9
- Spring Boot 3.0.1

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/NikBud/SpaceX-API-Consuming.git
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

---

## Endpoints

### 1. Get All Rockets
Fetch details of all SpaceX rockets.

**Endpoint:**
```http
GET http://localhost:8080/spaceX/rockets
```

**Sample Response:**
```json
[
  {
    "id": "falcon1",
    "name": "Falcon 1",
    "description": "The first rocket developed by SpaceX."
  },
  {
    "id": "falcon9",
    "name": "Falcon 9",
    "description": "The most frequently flown rocket by SpaceX."
  }
]
```

### 2. Get Rocket by ID
Fetch details of a specific rocket by its ID.

**Endpoint:**
```http
GET /api/v1/rockets/{id}
```

**Path Parameters:**
- `id` - The unique identifier of the rocket (e.g., `falcon9`).

**Sample Response:**
```json
{
  "id": "falcon9",
  "name": "Falcon 9",
  "description": "The most frequently flown rocket by SpaceX.",
  "height": {
    "meters": 70,
    "feet": 229.6
  },
  "diameter": {
    "meters": 3.7,
    "feet": 12.1
  }
}
```

---

## Key Technologies

- **Spring Boot**: Framework for building Java-based applications.
- **Spring WebClient**: Reactive library for making HTTP requests.
- **PostgreSQL**: SQL database to ctock info about rockets.
- **JSON**: Data format for interacting with the SpaceX API.

---

## Challenges

The primary challenge in this project was managing the large and complex model structure returned by the SpaceX API. To address this:

1. **Nested Models:** Created a well-structured hierarchy of Java classes to map the JSON responses.
2. **Data Validation:** Ensured that optional fields are handled gracefully to avoid `NullPointerExceptions`.
3. **Scalability:** Designed the service to be extensible for future API integrations.

---

## Future Enhancements

- Add caching to improve performance for frequently requested data.
- Implement a frontend to visualize rocket data interactively.
- Add tests for enhanced reliability and code coverage.
- Extend support for additional SpaceX API endpoints, such as launches and payloads.

---

## Acknowledgments

- [SpaceX API](https://docs.spacexdata.com/) for providing the rocket data.
- Spring Boot and WebClient documentation for excellent resources on building RESTful applications.

 