# Full-Stack Project Development Workflow

## Purpose
High-level skill for coordinating full-stack development across Spring Boot backend, Angular frontend, and Docker infrastructure in the Necform project.

## Project Overview
**Necform** - A microservices-based application with:
- **Backend**: Spring Boot 4.1.0 microservices (Java 17)
- **Frontend**: Angular 22.0.0 (TypeScript)
- **Database**: PostgreSQL 16
- **Authentication**: Keycloak 26.2
- **Infrastructure**: Docker Compose orchestration
- **Architecture**: API Gateway pattern with Eureka service discovery

## Project Structure
```
Necform/
├── necform/                    # Main Spring Boot backend
│   ├── src/main/java/org/sid/necform/
│   │   ├── controller/         # REST controllers
│   │   ├── service/           # Business logic
│   │   ├── repository/        # Data access
│   │   ├── entity/           # JPA entities
│   │   ├── dto/              # Data transfer objects
│   │   ├── config/           # Configuration classes
│   │   └── security/         # Security configuration
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── necform-front/             # Angular frontend
│   ├── src/app/
│   │   ├── core/             # Core services, guards
│   │   ├── features/         # Feature modules
│   │   ├── shared/           # Shared components
│   │   └── models/           # TypeScript models
│   └── package.json
├── necform-gateway/          # API Gateway
├── necform-eureka-server/    # Service Discovery
├── necform-ai-service/       # AI Microservice
└── compose.yaml              # Docker orchestration
```

## Development Workflow

### 1. Feature Development Process

#### Git Workflow Setup
Before starting any development, follow the Git workflow:
```bash
# Create feature branch for the ticket
git checkout develop
git pull origin develop
git checkout -b feature/NEC-XXX-ticket-description

# Example for ticket NEC-001:
git checkout -b feature/NEC-001-apprenant-entity-repository
```

#### Backend Development
1. **Entity Design**: Create JPA entity with proper annotations
2. **Repository Layer**: Define repository interface
3. **Service Layer**: Implement business logic with transactions
4. **Controller Layer**: Create REST endpoints with validation
5. **Security**: Add role-based access control
6. **Documentation**: Add OpenAPI/Swagger annotations
7. **Testing**: Write unit and integration tests
8. **API Verification**: Test with Swagger UI
9. **Git Commit**: Commit changes with ticket reference
   ```bash
   git add .
   git commit -m "NEC-XXX: Backend change description"
   ```

#### Frontend Development
1. **Model Definition**: Create TypeScript interfaces matching backend DTOs
2. **Service Creation**: Implement API communication service
3. **Component Development**: Build UI components with forms
4. **Routing**: Configure routes with guards
5. **Authentication**: Integrate Keycloak authentication
6. **Error Handling**: Add proper error handling and loading states
7. **Testing**: Write component and service tests
8. **UI Verification**: Test user interface and flows
9. **Git Commit**: Commit changes with ticket reference
   ```bash
   git add .
   git commit -m "NEC-XXX: Frontend change description"
   ```

#### Integration
1. **API Contract**: Ensure frontend models match backend DTOs
2. **Authentication**: Configure Keycloak clients
3. **CORS**: Configure cross-origin resource sharing
4. **Environment**: Set up environment-specific configurations
5. **End-to-End Testing**: Test complete user flows
6. **Git Push**: Push feature branch and create PR
   ```bash
   git push origin feature/NEC-XXX-ticket-description
   # Create Pull Request to develop branch
   ```

### 2. Common Development Commands

#### Full Stack Development
```bash
# Start infrastructure (PostgreSQL + Keycloak)
docker-compose up -d postgres keycloak

# Start backend (in necform directory)
cd necform
.\mvnw spring-boot:run

# Start frontend (in necform-front directory)
cd necform-front
npm start
```

#### Build & Package
```bash
# Build backend
cd necform
.\mvnw clean package

# Build frontend
cd necform-front
npm run build

# Build Docker images
docker build -t necform-backend:latest ./necform
docker build -t necform-frontend:latest ./necform-front
```

#### Testing
```bash
# Backend tests
cd necform
.\mvnw test

# Frontend tests
cd necform-front
npm test

# Integration tests
docker-compose up -d
.\mvnw verify
```

### 3. Feature Implementation Template

#### Adding a New Entity (Backend + Frontend)

**Step 1: Backend Entity**
```java
// necform/src/main/java/org/sid/necform/entity/NewEntity.java
@Entity
@Table(name = "new_entities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
}
```

**Step 2: Backend Repository**
```java
// necform/src/main/java/org/sid/necform/repository/NewEntityRepository.java
@Repository
public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {
    Optional<NewEntity> findByName(String name);
}
```

**Step 3: Backend Service**
```java
// necform/src/main/java/org/sid/necform/service/NewEntityService.java
@Service
@RequiredArgsConstructor
@Transactional
public class NewEntityService {
    private final NewEntityRepository repository;
    
    public NewEntityDTO create(NewEntityDTO dto) {
        NewEntity entity = new NewEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return convertToDTO(repository.save(entity));
    }
    
    // CRUD operations...
}
```

**Step 4: Backend Controller**
```java
// necform/src/main/java/org/sid/necform/controller/NewEntityController.java
@RestController
@RequestMapping("/api/new-entities")
@RequiredArgsConstructor
@Tag(name = "New Entity Management")
public class NewEntityController {
    private final NewEntityService service;
    
    @GetMapping
    @Operation(summary = "Get all entities")
    public ResponseEntity<List<NewEntityDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create entity")
    public ResponseEntity<NewEntityDTO> create(@Valid @RequestBody NewEntityDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
```

**Step 5: Frontend Model**
```typescript
// necform-front/src/app/models/new-entity.model.ts
export interface NewEntity {
  id?: number;
  name: string;
  description?: string;
}
```

**Step 6: Frontend Service**
```typescript
// necform-front/src/app/core/services/new-entity.service.ts
@Injectable({ providedIn: 'root' })
export class NewEntityService {
  private apiUrl = 'http://localhost:8080/api/new-entities';
  
  constructor(private http: HttpClient) {}
  
  getAll(): Observable<NewEntity[]> {
    return this.http.get<NewEntity[]>(this.apiUrl);
  }
  
  create(entity: NewEntity): Observable<NewEntity> {
    return this.http.post<NewEntity>(this.apiUrl, entity);
  }
}
```

**Step 7: Frontend Component**
```typescript
// necform-front/src/app/features/new-entities/new-entities.component.ts
@Component({
  selector: 'app-new-entities',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './new-entities.component.html'
})
export class NewEntitiesComponent implements OnInit {
  entities: NewEntity[] = [];
  newEntity: NewEntity = { name: '' };
  
  constructor(private service: NewEntityService) {}
  
  ngOnInit(): void {
    this.loadEntities();
  }
  
  loadEntities(): void {
    this.service.getAll().subscribe(data => this.entities = data);
  }
  
  createEntity(): void {
    this.service.create(this.newEntity).subscribe(() => {
      this.loadEntities();
      this.newEntity = { name: '' };
    });
  }
}
```

### 4. Authentication & Authorization Flow

#### Backend Configuration
```java
// Security configuration with Keycloak
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter()))
            );
        return http.build();
    }
}
```

#### Frontend Configuration
```typescript
// Keycloak configuration
export const keycloakConfig = {
  url: 'http://localhost:8081',
  realm: 'necform',
  clientId: 'necform-frontend',
  credentials: {
    secret: '' // For confidential clients
  }
};
```

### 5. Database Migration Strategy

#### Schema Changes
1. Update entity classes
2. Create migration script if needed
3. Test migration on development database
4. Document changes
5. Apply to production with backup

#### Migration Commands
```bash
# Backup database before migration
docker exec necform-postgres pg_dump -U necform necform > backup.sql

# Apply migration (if using Flyway/Liquibase)
.\mvnw flyway:migrate

# Verify migration
docker exec -it necform-postgres psql -U necform -d necform -c "\dt"
```

### 6. API Documentation

#### Swagger UI Access
- **URL**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`

#### Adding Documentation
```java
@Operation(summary = "Create new entity", description = "Creates a new entity with validation")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Entity created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input"),
    @ApiResponse(responseCode = "403", description = "Access denied")
})
public ResponseEntity<EntityDTO> createEntity(@Valid @RequestBody EntityDTO dto) {
    // Implementation
}
```

### 7. Environment Configuration

#### Backend (application.properties)
```properties
# Development
spring.datasource.url=jdbc:postgresql://localhost:5432/necform
spring.datasource.username=necform
spring.datasource.password=necform

# Production
# spring.datasource.url=jdbc:postgresql://postgres:5432/necform
```

#### Frontend (environment.ts)
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api',
  keycloakUrl: 'http://localhost:8081'
};
```

### 8. Testing Strategy

#### Backend Testing
- Unit tests for services and repositories
- Integration tests for controllers
- Security tests for authorization
- Contract tests for API validation

#### Frontend Testing
- Unit tests for components
- Service tests with mocked HTTP
- Guard tests for authentication
- Integration tests for user flows

#### End-to-End Testing
- Test complete user journeys
- Verify authentication flows
- Test error handling
- Validate data consistency

### 9. Deployment Process

#### Local Development
```bash
# Start infrastructure
docker-compose up -d postgres keycloak

# Start services
cd necform && .\mvnw spring-boot:run
cd necform-front && npm start
```

#### Production Deployment
```bash
# Build all services
.\mvnw clean package
cd necform-front && npm run build

# Build Docker images
docker-compose build

# Deploy
docker-compose up -d
```

### 10. Troubleshooting Guide

#### Common Issues

**Backend won't start**
- Check database connectivity: `docker-compose ps postgres`
- Verify configuration in application.properties
- Check port conflicts: `netstat -ano | findstr :8080`

**Frontend can't connect to backend**
- Verify backend is running: `curl http://localhost:8080/actuator/health`
- Check CORS configuration
- Verify API URL in environment files

**Authentication failures**
- Check Keycloak is running: `docker-compose ps keycloak`
- Verify Keycloak client configuration
- Check realm settings and user roles

**Database connection issues**
- Verify PostgreSQL container: `docker exec -it necform-postgres psql -U necform -d necform`
- Check database credentials
- Verify network connectivity

### 11. Best Practices

#### Code Quality
- Follow SOLID principles
- Write meaningful commit messages
- Add proper error handling
- Implement logging
- Write tests for new features
- Keep functions small and focused

#### Security
- Never commit secrets
- Use environment variables for sensitive data
- Implement proper authentication
- Validate all inputs
- Use HTTPS in production
- Regularly update dependencies

#### Performance
- Use database indexes appropriately
- Implement caching where needed
- Optimize database queries
- Use lazy loading for JPA relationships
- Implement pagination for large datasets
- Monitor application performance

#### Collaboration
- Use feature branches
- Create pull requests for review
- Document complex logic
- Follow coding standards
- Communicate changes effectively

### 12. Project-Specific Patterns

#### DTO Pattern
- Use DTOs for API contracts
- Map entities to DTOs in service layer
- Validate DTOs with Bean Validation
- Keep DTOs separate from entities

#### Service Pattern
- Business logic in service layer
- Transactions at service level
- Exception handling in services
- Logging in services

#### Repository Pattern
- Extend JpaRepository for CRUD
- Custom queries with @Query
- Specification for complex queries
- Pagination and sorting support

### 13. Monitoring & Logging

#### Application Monitoring
- Spring Boot Actuator endpoints
- Health checks: `/actuator/health`
- Metrics: `/actuator/metrics`
- Application logs: Check console or file

#### Frontend Monitoring
- Browser console for errors
- Network tab for API calls
- Performance monitoring
- Error tracking

#### Infrastructure Monitoring
- Container logs: `docker-compose logs`
- Container stats: `docker stats`
- Resource usage monitoring
- Health check status

## Quick Reference

### Essential Ports
- **Frontend**: `http://localhost:4200`
- **Backend API**: `http://localhost:8080`
- **PostgreSQL**: `localhost:5432`
- **Keycloak**: `http://localhost:8081`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Eureka**: `http://localhost:8761`
- **Gateway**: `http://localhost:8080`

### Default Credentials
- **PostgreSQL**: `necform` / `necform`
- **Keycloak Admin**: `admin` / `admin`

### Key Commands
```bash
# Start everything
docker-compose up -d

# Stop everything
docker-compose down

# Backend build
cd necform && .\mvnw clean package

# Frontend build
cd necform-front && npm run build

# Backend tests
cd necform && .\mvnw test

# Frontend tests
cd necform-front && npm test
```

## Important Notes
- Always backup database before schema changes
- Test authentication flows thoroughly
- Keep API contracts stable
- Document breaking changes
- Monitor container resource usage
- Use proper git branching strategy
- Review security configurations regularly
- Keep dependencies updated
- Test on multiple browsers
- Implement proper error handling in all layers