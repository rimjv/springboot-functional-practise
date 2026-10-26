# Task Manager REST API

A practice REST API built with **Spring Boot** and **Java** to learn how backend services work: controllers, repositories, databases, validation, and testing. The API manages a simple to-do list (tasks).

## Tech Stack

- Java 17+
- Spring Boot
- Spring Web (REST endpoints)
- Spring Data JPA
- H2 in-memory database
- Bean Validation
- Gradle

## Project Status

- [x] Project setup and GitHub connection
- [x] Health check endpoint
- [ ] Task entity and repository
- [ ] CRUD endpoints for tasks
- [ ] Validation and error handling
- [ ] Unit and integration tests
- [ ] Pagination, filtering, and sorting

## Getting Started

### Prerequisites

- JDK 17 or newer
- Git
- IntelliJ IDEA (optional, any IDE works)

### Clone the repository

```
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
```

### Run the application

Windows (PowerShell):

```
.\gradlew.bat bootRun
```

Mac/Linux:

```
./gradlew bootRun
```

The server starts on `http://localhost:8080`.

### Run the tests

Windows (PowerShell):

```
.\gradlew.bat test
```

Mac/Linux:

```
./gradlew test
```

## API Endpoints

### Available now

| Method | Endpoint  | Description  |
|--------|-----------|--------------|
| GET    | `/health` | Health check |

Example:

```
curl http://localhost:8080/health
```

Response:

```json
{ "status": "ok" }
```

### Planned

| Method | Endpoint      | Description         |
|--------|---------------|---------------------|
| GET    | `/tasks`      | List all tasks      |
| GET    | `/tasks/{id}` | Get a task by ID    |
| POST   | `/tasks`      | Create a new task   |
| PUT    | `/tasks/{id}` | Update a task       |
| DELETE | `/tasks/{id}` | Delete a task       |

Example request body for creating a task:

```json
{
  "title": "Learn Spring Boot",
  "description": "Build a REST API step by step",
  "status": "TODO"
}
```

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java      # Application entry point
├── HealthController.java     # Health check endpoint
├── controller/               # REST controllers (planned)
├── model/                    # JPA entities (planned)
└── repository/               # Data access layer (planned)
```

## Key Concepts

### Why is there a HealthController?

`HealthController` is a simple "are you alive?" check. It has no database or business logic behind it. It just returns `{"status": "ok"}` when you call `GET /health`.

It is useful because:
- It confirms the app started and the web layer is working, before any real features exist.
- Monitoring tools, load balancers, and deployment platforms (Docker, Kubernetes, cloud services) commonly call a health endpoint to check whether a service is running.
- It is the smallest possible example of a controller, so it is a good first test of the setup.

### Why is there a DemoApplication.java?

`DemoApplication.java` is the **application entry point**. It is the class that holds the `main` method, which is where Java starts running the program.

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

When it runs, Spring Boot:
1. Starts an embedded web server (Tomcat) on port 8080.
2. Creates the Spring application context, which holds all the app's components.
3. Scans this class's package and its sub-packages for controllers, services, and repositories (this is why `HealthController` is found automatically).
4. Applies auto-configuration based on the dependencies on the classpath, such as the database and JPA.

Every Spring Boot application has exactly one such class, annotated with `@SpringBootApplication` and containing a `main` method. Its name can be anything, and `<ProjectName>Application` is just the convention. All other classes (controllers, entities, repositories) live in the same package or below it.

## Notes

- The H2 database is in-memory, so data resets every time the app restarts.
- Keep the project outside synced folders such as OneDrive to avoid Gradle build file locking issues on Windows.

## What I'm Learning

- Building REST APIs with Spring Boot
- Layered architecture: controller, repository, entity
- Working with JPA and an embedded database
- Using Git and GitHub for version control

## License

This project is for learning purposes.
