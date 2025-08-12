# CaramelWaffle-Springboot-Microservice-bootcamp

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
        - Run the existing container `docker start {containerId}`
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
    - Active a difference profile via arg `--spring.profiles.active=uat` or
      `mvn spring-boot:run -Dspring-boot.run.profiles=uat`
- **Spring Cloud Config** centralized configuration server for facilitate, manage and distribution to multiple
  applications
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
      `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management`
    - Enable RabbitMQ in `application.yml`
    - Publish refresh event using `POST http://localhost:8080/actuator/bus-refresh`
- Checking configuration microservice health, readiness and liveness using Spring Boot Actuator
    - Enable actuator endpoints in `application.yml`
    - Access health endpoint via `http://localhost:8071/actuator/health`
    - Access readiness endpoint via `http://localhost:8071/actuator/health/readiness`
    - Access liveness endpoint via `http://localhost:8071/actuator/health/liveness`
- Adding healthcheck on Dockerfile to ensure the required microservice is ready before starting other microservices
- Generate new images using **GoogleJib**

---

### Chapter 4: Using MySQL Database in Microservices

- Install MySQL via Docker
    - Create a new MySQL Database
        - account db
          `docker run -p 3306:3306 --name account-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=account-db -d mysql:latest`
        - loan db
          `docker run -p 3307:3306 --name loan-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loan-db -d mysql:latest`
        - loan db
          `docker run -p 3308:3306 --name card-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=card-db -d mysql:latest`
    - Update application.yml to connect to MySQL
        - Set `spring.datasource.url=jdbc:mysql://localhost:3306/account-db`
        - Set `spring.datasource.username=root`
        - Set `spring.datasource.password=root`
    - Update docker image
        - build image using `mvn compile jib:dockerBuild`

---

### Chapter 5: Service Discovery and Service Registry

- **Service Discovery**: `Spring Cloud Netflix Eureka`
    - Automatically detects and registers services in a microservices architecture.
    - Client Side Discovery: Clients query the service registry to find service instances.
    - Server Side Discovery: Clients send requests to a load balancer, which queries the service registry and forwards
      the request to an available service instance.
- **Service Registry**: A central database that stores the network locations of service instances.
- **Load Balancing**: `Spring Cloud Load Balancer`
    - Distributes requests across multiple service instances to ensure high availability and reliability.
- **Feign Client** : `Netfix Feign Client`
    - A declarative HTTP client that simplifies service-to-service communication.
- Implement
    - Create discovery server using Eureka
        - Add `spring-cloud-starter-netflix-eureka-server` dependency to `pom.xml`
        - Add `@EnableEurekaServer` annotation to the main application class
        - Configure Eureka server in `application.yml` or config server
            - Set `eureka.client.register-with-eureka=false`
            - Set `eureka.client.fetch-registry=false`
            - Set `eureka.server.enable-self-preservation=false`
            - set `eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/`
        - access Eureka server via `http://localhost:8070/`
        - Register microservices to Eureka server
        - Configure Eureka client in `application.yml` to send service information to discovery server
            - set `management.info.env.enabled=true`
            - set `eureka.instance.prefer-ip-address=true`
            - set `eureka.client.fetch-registry=true`
            - set `eureka.client.register-with-eureka=true`
            - set `eureka.client.service-url.defaultZone=http://localhost:8070/eureka/`
    - Communicate between microservices using **Feign Client**
        - Add `spring-cloud-starter-openfeign` dependency to `pom.xml`
        - Enable Feign Client in the main application class using `@EnableFeignClients`
        - Create Feign Client interface for each microservice
            - Use `@FeignClient(name = "service-name")` annotation
            - Define methods with `@GetMapping`, `@PostMapping`, etc.
            - Create data transfer objects (DTOs) for request and response
        - Use Feign Client in service classes to call other microservices
    - Build image using `mvn compile jib:dockerBuild`

---

### Chapter 6: API Gateway and Dynamic Routing

- **API Gateway**: A single entry point for all client requests, routing them to the appropriate microservice.
- **Dynamic Routing**: The ability to route requests to different microservices based on various criteria, such as
  request headers, parameters, or paths.
- What API Gateway can do:
    - Request Validation
    - White-listing
    - Authentication
    - Rate Limiting
    - Dynamic Routing
    - Service Discovery
    - Modification of Request and Response
    - Protocol Conversion
    - Circuit Breaker
    - Resilience
- Spring Cloud Gateway Internal Architecture:
    - **Route**: A route is a definition of how to route requests to a specific service.
    - **Predicate**: A predicate is a condition that must be met for a route to be applied.
    - **Filter**: Filters are used to modify the request or response, such as adding headers or changing the response
      body.
- Create API Gateway Server using `Spring Cloud Gateway`
    - Config port to `8072`
    - Config Spring Cloud Config server
    - Config Eureka client
      ```management.info.env.enabled=true
        management.endpoint.gateway.access=unrestricted
        management.endpoints.web.exposure.include=*
        spring.cloud.gateway.server.webflux.discovery.locator.enabled=true
        spring.cloud.gateway.server.webflux.discovery.locator.lower-case-service-id=true
    - Start the gateway server on latest service to receive the latest service information from Eureka server
    - Access the gateway server via `http://localhost:8072/`
    - Access the account service via `http://localhost:8072/accounts`, example http://localhost:8072/accounts/api/create
- Implement dynamic routing using `RouteLocator`
    - Create a `@Configuration` class to define routes
    - Use `RouteLocatorBuilder` to create routes dynamically
    - Define predicates and filters for each route
    - Example:
      ```java
      @Bean
      public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
          return builder.routes()
              .route("account_route", r -> r.path("/accounts/**")
                  .uri("lb://accounts-service"))
              .route("loan_route", r -> r.path("/loans/**")
                  .uri("lb://loans-service"))
              .build();
      }
      ```
- Implement filter `addRequestHeader` to add custom headers to the request
- Implement filter `addResponseHeader` to add custom headers to the response
- Implement filter `modifyResponseBody` to modify the response body
- Create a custom filter to write correlation ID to the response header
    - Use `ServerWebExchange` to access the request and response
    - Generate a unique correlation ID for each request
    - Add the correlation ID to the response header
    - Log the correlation ID for tracing using Logger
- Build image using `mvn compile jib:dockerBuild`
- Push the image to DockerHub using ` docker image push docker.io/kodomochi/gatewayserver:v6`
- Update `docker-compose.yml` to include the API Gateway service
    - Add the gateway service configuration
    - Set the image to `kodomochi/gatewayserver:v6`
    - Set the container name to `gateway-server`
    - Set the ports to `8072:8072`
    - Set the networks to `wafflebank-network`


---

### Chapter 7: Make Microservices Resilient using Circuit Breaker
- When one microservice is down, it can cause a cascading failure in the entire system. We can prevent this by using
  fallback mechanisms and circuit breakers.
- `Resilience4j`: A lightweight fault tolerance library designed for Java applications, providing various patterns to
  handle failures gracefully.
  - **Circuit Breaker**: A design pattern that prevents a service from making requests to a failing service, allowing it to
    recover and preventing cascading failures.
    - States:
      - **Closed**: Requests are allowed to pass through.
      - **Open**: Requests are blocked, and fallback logic is executed.
      - **Half-Open**: A limited number of requests are allowed to test if the service has recovered.
  - **Fallback**: Alternative logic that is executed when a service call fails, providing a graceful degradation of functionality.
  - **Retry**: Automatically retrying a failed request a certain number of times before giving up.
  - **Rate Limiting**: Limiting the number of requests a service can handle in a given time period to prevent overload.
  - **Bulkhead**: Limit the number of concurrent requests to a service, isolating it from other services.
- Import `spring-cloud-starter-circuitbreaker-reactor-resilience4j` dependency to `pom.xml`
- Adding resilience4j configuration to `application.yml`
    ```# Resilience4j configuration
    resilience4j.circuitbreaker.configs.default.slidingWindowSize=10
    resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState=2
    resilience4j.circuitbreaker.configs.default.failureRateThreshold=50
    resilience4j.circuitbreaker.configs.default.waitDurationInOpenState=10000
- Adding Circuit Breaker filter to the API Gateway
    ``.circuitBreaker(config -> config.setName("cardsCircuitBreaker"))``
  - When service is down, the API Gateway will return a fallback response (`"500 Service is currently unavailable"`).
  - When the service is up, the API Gateway will return the actual response from the service.
  - See the events log via `http://localhost:8072/actuator/circuitbreakerevents?name=accountsCircuitBreaker`
  - See the state of the circuit breaker via `http://localhost:8072/actuator/circuitbreakers`
- Adding Circuit Breaker to Feign Client
     - Adding `spring-cloud-starter-circuitbreaker-resilience4j` to `pom.xml` of Accounts microservice
     - Add `@CircuitBreaker` annotation to the Feign Client method
     - Define a fallback method to handle failures
     - Example:
       ```java
       @FeignClient(name = "loans", fallback = LoansFallback.class)
       @FeignClient(name = "card", fallback = CardsFallback.class)
       ```
- Configure HTTP client timeouts on Gateway server
  - set `spring.cloud.gateway.server.webflux.httpclient.connect-timeout=5000`
  - set `spring.cloud.gateway.server.webflux.httpclient.response-timeout=5000`
- Adding Retry mechanism to the API Gateway
  - Use `retry` filter in the route configuration
  - Example:
    ```
    filter(retry(config -> config.setRetries(3).setMethods(HttpMethod.GET)))
    ```
  - This will retry the request up to 3 times for GET requests if the service is down.
  - Implement retry ignoreExceptions and retryExceptions