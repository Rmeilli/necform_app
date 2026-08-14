# Spring Boot Backend Development

## Purpose
High-level skill for Spring Boot backend development in the Necform project. Handles microservice development, database operations, security, and API development.

## Project Structure
- **Backend Root**: `C:\Users\User\Downloads\Necform\necform`
- **Framework**: Spring Boot 4.1.0 with Java 17
- **Database**: PostgreSQL with JPA/Hibernate
- **Security**: Spring Security with Keycloak OAuth2
- **Build Tool**: Maven
- **Documentation**: SpringDoc OpenAPI (Swagger)

## Capabilities

### Database Operations
- Create new JPA entities with Lombok annotations
- Define repositories extending JpaRepository
- Add database indexes and constraints
- Write custom queries using @Query
- Handle database migrations

### REST API Development
- Create REST controllers with proper annotations
- Implement CRUD operations
- Add validation using @Valid and Bean Validation
- Define DTOs for request/response
- Add OpenAPI/Swagger documentation
- Handle exceptions with @ControllerAdvice

### Security & Authentication
- Configure Spring Security with Keycloak
- Add role-based access control (@PreAuthorize)
- Secure endpoints with appropriate annotations
- Handle JWT token validation
- Configure CORS policies

### Service Layer Development
- Create service interfaces and implementations
- Add business logic and validation
- Implement transaction management (@Transactional)
- Handle service exceptions
- Add logging

### Testing
- Write unit tests for services
- Create integration tests for controllers
- Test repository layer with @DataJpaTest
- Mock dependencies with Mockito
- Test security configurations

## Common Commands

### Build & Run
```bash
# Navigate to backend directory
cd necform

# Build project
.\mvnw clean install

# Run application
.\mvnw spring-boot:run

# Run with specific profile
.\mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Database Operations
```bash
# Start PostgreSQL via Docker Compose
docker-compose up -d postgres

# Access database
docker exec -it necform-postgres psql -U necform -d necform
```

### Testing
```bash
# Run all tests
.\mvnw test

# Run specific test class
.\mvnw test -Dtest=UtilisateurServiceTest

# Run with coverage
.\mvnw test jacoco:report
```

## Code Conventions

### Entity Structure
```java
@Entity
@Table(name = "entity_name")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    // Relationships
    @OneToMany(mappedBy = "entityName", cascade = CascadeType.ALL)
    private List<RelatedEntity> relatedEntities;
}
```

### Controller Structure
```java
@RestController
@RequestMapping("/api/entities")
@RequiredArgsConstructor
@Tag(name = "Entity Management", description = "Entity management endpoints")
public class EntityController {
    
    private final EntityService entityService;
    
    @GetMapping
    @Operation(summary = "Get all entities")
    public ResponseEntity<List<EntityDTO>> getAllEntities() {
        return ResponseEntity.ok(entityService.getAllEntities());
    }
    
    @PostMapping
    @Operation(summary = "Create new entity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityDTO> createEntity(@Valid @RequestBody EntityDTO dto) {
        return ResponseEntity.ok(entityService.createEntity(dto));
    }
}
```

### Service Structure
```java
@Service
@RequiredArgsConstructor
@Transactional
public class EntityService {
    
    private final EntityRepository entityRepository;
    
    public EntityDTO createEntity(EntityDTO dto) {
        Entity entity = convertToEntity(dto);
        Entity saved = entityRepository.save(entity);
        return convertToDTO(saved);
    }
}
```

## Configuration Files
- **Application Config**: `src/main/resources/application.properties`
- **Security Config**: `src/main/java/org/sid/necform/config/SecurityConfig.java`
- **Docker Compose**: `compose.yaml` (project root)

## Dependencies Added
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter OAuth2 Resource Server
- Spring Boot Starter Validation
- Spring Boot Starter Web MVC
- SpringDoc OpenAPI
- PostgreSQL Driver
- Lombok

## Development Workflow
1. **Git Setup**: Create feature branch for the ticket
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/NEC-XXX-ticket-description
   ```
2. Define entity with proper JPA annotations
3. Create repository interface
4. Implement service layer with business logic
5. Create REST controller with endpoints
6. Add security annotations if needed
7. Write tests for all layers
8. Add OpenAPI documentation
9. Test with Swagger UI at http://localhost:8080/swagger-ui.html
10. **Git Commit**: Commit changes with ticket reference
    ```bash
    git add .
    git commit -m "NEC-XXX: Backend change description"
    git push origin feature/NEC-XXX-ticket-description
    ```
11. **Pull Request**: Create PR to develop branch and request review

## Important Notes
- Use Lombok annotations to reduce boilerplate
- Always validate input with @Valid
- Use DTOs for API contracts
- Implement proper exception handling
- Add logging for important operations
- Follow RESTful conventions
- Test security configurations
- Keep database operations in service layer
- Use @Transactional for service methods that modify data