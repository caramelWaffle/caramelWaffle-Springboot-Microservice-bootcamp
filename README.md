# caramelWaffle-Springboot-Microservice-bootcamp

---
### Chapter 1: CRUD Account Microservice with H2 Database

- Configure `application.yml`
    - Set server port to `8080`
    - Configure internal H2 database connection
- Create `@Data` and `@Entity` classes
- Implement Builder pattern for entities
- Create network response model
- Create JPA repository class for CRUD operations
- Develop REST controller class
    - Implement the "create account" route using `@PostMapping`
    - Implement the "find account" route using  `@GetMapping`
    - Implement the "update account" route using `@PutMapping`
    - Use `@Transactional` and `@Modifying` when modifying the database values
- Return network HTTP results and handle errors
- Return a data object corresponding to the CRUD operation
- Create service class for CRUD functionality
- Define a custom exception class with appropriate `HttpStatus`
- Implement global exception handler using `@ControllerAdvice`
- Adding a default auditor for createdBy, updatedBy, createdAt, and updatedAt using `AuditorAware`
- Introduction to documentation of REST APIs using `Springdoc` openapi
- Assignment : Create Loan and Card microservices

---
### Chapter 2: Handle deployment, portable & scalability of microservices using Docker
- Definition of Containers, Docker
  - A container is a loosely isolated environment that allows us to build and run software packages (container images)
- Generate Docker Images 
  - DockerFile : Writing Dockerfile with
    - Generate Jar package using `mvn clean install` and .jar file via `java -jar target/accounts-0.0.1-SNAPSHOT.jar`
    - Writing Dockerfile
      - Start with base image containing Java runtime [`FROM openjdk:17-jdk-slim`]
      - Information about image maintainer [`LABEL maintainer`]
      - Add the application's jar to image [`COPY`]
      - Execute the application [`ENTRYPOINT`]
    - Build image and store to DockerHub using `docker build . -t kodomochi/account:v1`
    - Run image using `docker run -d -p 8080:8080 kodomochi/accounts:v1`
    - Run the existing  container `docker start {containerId}`
  - BuildPacks
  - GoogleJib