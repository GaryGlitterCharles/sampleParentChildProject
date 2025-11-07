# Demo Parent Project

A multi-module Maven project demonstrating a parent project with two modules:
- **commons**: Reusable utilities module containing request filters and utilities
- **service**: Spring Boot application that depends on commons and exposes REST endpoints

## Project Structure

```
demo-parent/
├── pom.xml                    # Parent POM
├── commons/                   # Commons module (jar)
│   ├── pom.xml
│   └── src/main/java/com/example/commons/
│       ├── filter/
│       │   └── RequestLoggingFilter.java
│       └── util/
│           └── RequestUtils.java
└── service/                   # Service module (Spring Boot)
    ├── pom.xml
    └── src/main/
        ├── java/com/example/service/
        │   ├── DemoServiceApplication.java
        │   ├── controller/
        │   │   └── HealthController.java
        │   └── config/
        │       └── FilterConfig.java
        └── resources/
            └── application.properties
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Build Instructions

To build all modules:

```bash
mvn -T 1C clean install
```

This command:
- Uses parallel builds with 1 thread per CPU core (`-T 1C`)
- Cleans previous build artifacts
- Compiles and packages all modules
- Installs artifacts to local Maven repository

## Run Instructions

To run the Spring Boot service application:

```bash
mvn -pl :service -am spring-boot:run
```

This command:
- `-pl :service` - Builds only the service module
- `-am` - Also builds required modules (commons in this case)
- `spring-boot:run` - Runs the Spring Boot application

## Testing the Application

Once the service is running, you can test the health endpoint:

```bash
curl http://localhost:8080/health
```

Expected response:
```json
{
  "status": "UP",
  "timestamp": "2024-01-01T12:00:00",
  "request": "GET /health [127.0.0.1]",
  "isLocalRequest": true,
  "message": "Service is healthy"
}
```

## Module Details

### Commons Module

Contains reusable utilities:
- **RequestLoggingFilter**: Servlet filter for logging HTTP requests
- **RequestUtils**: Utility class for request-related operations (IP address extraction, local request detection, etc.)

### Service Module

Spring Boot application that:
- Depends on the commons module
- Exposes a `/health` REST endpoint
- Uses commons utilities (RequestUtils) in the health controller
- Registers the RequestLoggingFilter to log all incoming requests

## Configuration

The service module uses `application.properties` for configuration:
- Server port: 8080
- Actuator endpoints: health, info
- Logging configuration

## Project Information

- **Group ID**: com.example
- **Parent Artifact ID**: demo-parent
- **Version**: 1.0.0
- **Java Version**: 17
- **Spring Boot Version**: 3.2.0

