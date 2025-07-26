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
- **DockerFile** to generate Docker Images:
  - Adding packaging `jar` to `pom.xml`
  - Generate Jar package using `mvn clean install` and .jar file via `java -jar target/accounts-0.0.1-SNAPSHOT.jar`
  - Writing Dockerfile
    - Start with base image containing Java runtime [`FROM openjdk:17-jdk-slim`]
    - Information about image maintainer [`LABEL maintainer`]
    - Add the application's jar to image [`COPY`]
    - Execute the application [`ENTRYPOINT`]
    - Build image and store to DockerHub using `docker build . -t kodomochi/account:v1`
    - Run image using `docker run -d -p 8080:8080 kodomochi/accounts:v1`
    - Run the existing  container `docker start {containerId}`
- **BuildPacks** to generate Docker Images
  - Adding packaging `jar` to `pom.xml`
  - Adding image name on `pom.xml`
  - Building image using `mvn spring-boot:build-image`
  - Run image using `docker run -d -p 8081:8090 kodomochi/loans:v1`
  - Size smaller compared to DockerFile
  - Recommended on large projects
- **GoogleJib** to generate Docker Images
  - Add jib-marven-plugin to `pom.xml` [https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin]
  - Generate image using `mvn compile jib:dockerBuild`
  - Run image using `docker run -d -p 9000:9000 kodomochi/card:v1`
  - Lightweight, recommend on local system
- Pushing Docker images from local to remote using `docker image push docker.io/kodomochi/loans:v1`
- **Docker Compose** define and run multi-container Docker images via YAML file
  - Creating `docker-compose.yml` file
    - Config microservices
      - image: kodomochi/accounts:v1
      - container_name: account-microservice
      - ports
      - deploy
      - networks
      - driver: "bridge" allows microservice can communicate with each other
    - Running all microservices containers using `docker compose up`

---
### Chapter 3: Configuration management in microservices

- Reading single configurations on application.yml using `@Value` annotation
- Reading group of configurations on application.yml using `@ConfigurationProperties` annotation
- Reading configurations using `Environment` interface
- Grouping configuration properties using **Profile**
  - Create separate env and configuration of `appliction.yml` file
  - Active a difference profile via arg `--spring.profiles.active=uat` or `mvn spring-boot:run -Dspring-boot.run.profiles=uat`
- **Spring Cloud Config** centralized configuration server for facilitate, manage and distribution to multiple applications
  - Create cloud config server using *Spring Cloud Config*
  - Add annotation `@EnableConfigServer` To Application Class
    - **Local Config**
      - Adding local config file to `src/main/resources/config`
      - Using local config file by `spring.profiles.include=native` and set file path
        `spring.cloud.config.server.native.search-locations="classpath:/config"`
      - Access configuration value via `http://localhost:8071/accounts/prod`
    - **Remote Config - GitHub**
      - Adding remote config to GitHub repository
      - Using remote config file by `spring.profiles.active=git` and set git url
        `spring.cloud.config.server.git.uri=https://github.com/caramelWaffle/wafflebank-config.git
- Refresh configurations at runtime using refresh endpoint
  - Add `spring-boot-starter-actuator` dependency to `pom.xml`
  - Enable refresh endpoint by adding `management.endpoints.web.exposure.include=*` to `application.yml`
  - Enable `@RefreshScope` to the class that needs to refresh configurations
  - Refresh configurations using `POST http://localhost:808080/actuator/refresh`
- Refresh multiple microservice configurations at runtime using Spring Cloud Bus
  - Add `spring-cloud-starter-bus-amqp` dependency to `pom.xml`
  - Install RabbitMQ via Docker
  - Enable RabbitMQ in `application.yml`
  - Publish refresh event using `POST http://localhost:8080/actuator/bus-refresh`
- Checking configuration microservice health, readiness and liveness using Spring Boot Actuator
  - Enable actuator endpoints in `application.yml`
  - Access health endpoint via `http://localhost:8071/actuator/health`
  - Access readiness endpoint via `http://localhost:8071/actuator/health/readiness`
  - Access liveness endpoint via `http://localhost:8071/actuator/health/liveness`