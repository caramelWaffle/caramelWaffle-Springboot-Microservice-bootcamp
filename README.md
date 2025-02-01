# caramelWaffle-Springboot-Microservice-bootcamp

---
### Chapter 1: Create Account Microservice with H2 Database

- Configure `application.yml`
    - Set server port to `8080`
    - Configure internal H2 database connection
- Create `@Data` and `@Entity` classes
- Implement Builder pattern for entities
- Create network response model
- Create JPA repository class for CRUD operations
- Develop REST controller class
    - Implement routes with `@PostMapping` and `@GetMapping`
- Return network HTTP results and handle errors
- Create service class for CRUD functionality
- Define a custom exception class with appropriate `HttpStatus`
- Implement global exception handler using `@ControllerAdvice`
