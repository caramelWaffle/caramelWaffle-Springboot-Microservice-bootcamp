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
