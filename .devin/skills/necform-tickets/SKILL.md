# Necform Project Development Tickets

## Purpose
Comprehensive development tickets for the Necform training management platform project (13-week internship project). Organized by functional perimeter with technical strategy and development steps.

## Project Overview
**Necform** - Training management platform with AI assistant for IT/DevOps/Cloud/Data/IA training organization.

### Timeline: 13 Weeks
- **S1-S2**: Analysis and conception
- **S3-S5**: Backend development
- **S6-S8**: Frontend development
- **S9-S10**: Document generation
- **S11-S12**: Dashboard and AI
- **S13**: Testing, documentation, and presentation

### Technology Stack
- **Frontend**: Angular 22.0.0
- **Backend**: Spring Boot 4.1.0
- **Database**: PostgreSQL 16
- **Authentication**: JWT + Keycloak
- **Documentation**: Swagger
- **Containerization**: Docker
- **Deployment**: VPS or Azure

---

## GIT WORKFLOW FOR TICKETS

### Branch Strategy
Each development ticket follows this Git workflow:

1. **Create Development Branch**
   ```bash
   # Format: feature/NEC-XXX-ticket-description
   git checkout -b feature/NEC-001-apprenant-entity-repository
   ```

2. **Development Work**
   - Implement the ticket according to technical strategy
   - Follow development steps provided
   - Commit changes with descriptive messages
   ```bash
   git add .
   git commit -m "NEC-001: Create Apprenant entity and repository"
   ```

3. **Testing & Validation**
   - Run tests locally
   - Verify functionality meets requirements
   - Ensure code follows project standards

4. **Push & Create Pull Request**
   ```bash
   git push origin feature/NEC-001-apprenant-entity-repository
   ```
   - Create PR from feature branch to `develop` branch
   - Include ticket number in PR title
   - Reference ticket in description
   - Request review from Product Owner

5. **Review & Merge**
   - PO reviews the changes
   - Requests modifications if needed
   - Approves and merges to `develop` branch
   - Delete feature branch after merge

6. **Ticket Status Update**
   - Update ticket status to "Terminé"
   - Move to appropriate column in project management

### Branch Naming Convention
- **Format**: `feature/NEC-XXX-ticket-description`
- **Example**: `feature/NEC-001-apprenant-entity-repository`
- **Keep it**: Short, descriptive, and lowercase

### Commit Message Convention
- **Format**: `NEC-XXX: Description of changes`
- **Example**: `NEC-001: Create Apprenant entity with JPA annotations`
- **Keep it**: Concise and imperative mood

### Pull Request Template
```markdown
## Ticket #NEC-XXX: [Ticket Title]

### Description
[Brief description of what was implemented]

### Changes Made
- [List of key changes]
- [Files modified]
- [New features added]

### Testing
- [Unit tests passed]
- [Integration tests passed]
- [Manual testing completed]

### Screenshots (if applicable)
[Add screenshots for UI changes]

### Checklist
- [ ] Code follows project standards
- [ ] Tests added/updated
- [ ] Documentation updated
- [ ] No breaking changes
```

---

## ESPACE: NECFORM

### Team Roles
- **Product Owner**: Product Owner (PO)
- **Backend Developer**: Backend Dev
- **Frontend Developer**: Frontend Dev
- **Full Stack Developer**: Full Stack Dev

---

## MODULE 1: GESTION DES APPRENANTS

### Ticket #NEC-001: Backend - Apprenant Entity & Repository
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S3

#### Git Workflow
```bash
# Create development branch
git checkout -b feature/NEC-001-apprenant-entity-repository

# Development work...
git add .
git commit -m "NEC-001: Create Apprenant entity and repository"
git push origin feature/NEC-001-apprenant-entity-repository

# Create PR to develop branch
```

#### Technical Strategy
- Create JPA entity for Apprenant with complete attributes
- Implement repository with custom queries for search and filtering
- Add validation annotations and database constraints
- Set up relationships with Formation and Session entities

#### Development Steps
1. Create `Apprenant` entity with fields: id, nom, prenom, email, telephone, adresse, dateNaissance, niveauEtude, statut, dateInscription
2. Add JPA annotations: @Entity, @Table, @Column constraints
3. Create `ApprenantRepository` extending JpaRepository
4. Add custom queries: findByEmail, findByStatut, searchByNomPrenom
5. Add validation: @Email, @NotBlank, @Pattern for phone
6. Create database indexes on email and nom
7. Write unit tests for repository

---

### Ticket #NEC-002: Backend - Apprenant Service Layer
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S3

#### Git Workflow
```bash
git checkout -b feature/NEC-002-apprenant-service-layer
# Development work...
git commit -m "NEC-002: Implement Apprenant service layer"
git push origin feature/NEC-002-apprenant-service-layer
# Create PR to develop branch
```

#### Technical Strategy
- Implement service layer with business logic
- Add transaction management
- Implement exception handling
- Add logging for audit trail
- Create DTOs for API contracts

#### Development Steps
1. Create `ApprenantDTO` with request/response mapping
2. Create `ApprenantService` interface and implementation
3. Implement CRUD operations: create, update, delete, findById, findAll
4. Add business validation: unique email, valid phone format
5. Implement search with pagination and sorting
6. Add @Transactional annotations
7. Create custom exceptions: ApprenantNotFoundException, EmailAlreadyExistsException
8. Add logging with SLF4J
9. Write unit tests with Mockito

---

### Ticket #NEC-003: Backend - Apprenant REST Controller
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S3

#### Git Workflow
```bash
git checkout -b feature/NEC-003-apprenant-rest-controller
# Development work...
git commit -m "NEC-003: Create Apprenant REST controller"
git push origin feature/NEC-003-apprenant-rest-controller
# Create PR to develop branch
```

#### Technical Strategy
- Create REST endpoints with proper HTTP methods
- Add OpenAPI/Swagger documentation
- Implement role-based access control
- Add input validation
- Handle exceptions globally

#### Development Steps
1. Create `ApprenantController` with @RestController
2. Implement endpoints: GET /api/apprenants, POST /api/apprenants, GET /api/apprenants/{id}, PUT /api/apprenants/{id}, DELETE /api/apprenants/{id}
3. Add @PreAuthorize for role-based access
4. Add @Valid for request validation
5. Implement pagination with Pageable
6. Add OpenAPI annotations: @Operation, @ApiResponses
7. Create @ControllerAdvice for exception handling
8. Write integration tests with MockMvc

---

### Ticket #NEC-004: Frontend - Apprenant List Component
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S6

#### Technical Strategy
- Create Angular component for apprenant listing
- Implement data table with pagination
- Add search and filter functionality
- Integrate with backend API
- Add loading states and error handling

#### Development Steps
1. Create `Apprenant` interface matching backend DTO
2. Create `ApprenantService` with HTTP calls
3. Generate `ApprenantsListComponent` with Angular CLI
4. Implement template with Bootstrap table
5. Add pagination controls
6. Implement search filter by name/email
7. Add loading spinner and error messages
8. Implement delete confirmation modal
9. Add navigation to detail view
10. Write unit tests

---

### Ticket #NEC-005: Frontend - Apprenant Form Component
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S6

#### Technical Strategy
- Create reactive form for apprenant creation/editing
- Add form validation
- Implement form state management
- Add success/error feedback
- Integrate with backend API

#### Development Steps
1. Create `ApprenantFormComponent` with Angular CLI
2. Build reactive form with FormBuilder
3. Add form validators: required, email, pattern
4. Implement form submission with service
5. Add success/error notifications
6. Implement cancel functionality
7. Add form mode (create/edit) handling
8. Write unit tests for form validation

---

### Ticket #NEC-006: Frontend - Apprenant Detail Component
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S6

#### Technical Strategy
- Create detail view for apprenant
- Display complete apprenant information
- Show related formations and sessions
- Add edit and delete actions
- Implement navigation breadcrumbs

#### Development Steps
1. Create `ApprenantDetailComponent`
2. Fetch apprenant by ID from service
3. Display apprenant information with Bootstrap cards
4. Add related formations list
5. Add related sessions list
6. Implement edit and delete buttons
7. Add breadcrumb navigation
8. Handle loading and error states
9. Write unit tests

---

## MODULE 2: GESTION DES ENTREPRISES CLIENTES

### Ticket #NEC-007: Backend - Entreprise Entity & Repository
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S3

#### Technical Strategy
- Create Entreprise entity with company information
- Implement repository with business-specific queries
- Add relationship with contacts and sessions
- Set up validation for SIRET, TVA numbers

#### Development Steps
1. Create `Entreprise` entity: id, raisonSociale, siret, numeroTVA, adresse, telephone, email, secteur, dateCreation, statut
2. Add validation annotations for SIRET format
3. Create `EntrepriseRepository` with custom queries
4. Implement findBySiret, findBySecteur, searchByRaisonSociale
5. Add database indexes on siret and raisonSociale
6. Write unit tests

---

### Ticket #NEC-008: Backend - Contact Entity & Repository
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S3

#### Technical Strategy
- Create Contact entity linked to Entreprise
- Implement repository for contact management
- Add relationship with entreprise
- Set up validation for contact information

#### Development Steps
1. Create `Contact` entity: id, nom, prenom, fonction, email, telephone, entreprise (ManyToOne)
2. Create `ContactRepository` extending JpaRepository
3. Add custom queries: findByEntreprise, findByEmail
4. Add cascade operations for entreprise relationship
5. Write unit tests

---

### Ticket #NEC-009: Backend - Entreprise Service & Controller
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S4

#### Technical Strategy
- Implement service layer for entreprise management
- Create REST endpoints with full CRUD
- Add business logic for SIRET validation
- Implement contact management within entreprise

#### Development Steps
1. Create `EntrepriseDTO` and `ContactDTO`
2. Implement `EntrepriseService` with CRUD operations
3. Add SIRET format validation (14 digits)
4. Implement contact management methods
5. Create `EntrepriseController` with REST endpoints
6. Add role-based access control
7. Add OpenAPI documentation
8. Write integration tests

---

### Ticket #NEC-010: Frontend - Entreprise Management Components
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S7

#### Technical Strategy
- Create entreprise list, form, and detail components
- Implement contact management within entreprise
- Add SIRET validation in frontend
- Create entreprise-specific search and filters

#### Development Steps
1. Create `Entreprise` and `Contact` interfaces
2. Create `EntrepriseService` with API calls
3. Generate `EntreprisesListComponent` with data table
4. Create `EntrepriseFormComponent` with contact sub-form
5. Add SIRET validation (regex pattern)
6. Implement contact list within entreprise detail
7. Add secteur filter and search
8. Write unit tests

---

## MODULE 3: GESTION DES FORMATIONS

### Ticket #NEC-011: Backend - Formation Entity & Repository
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S3

#### Technical Strategy
- Create Formation entity with course catalog information
- Implement repository with catalog-specific queries
- Add categorization and tagging
- Set up pricing and duration management

#### Development Steps
1. Create `Formation` entity: id, titre, description, categorie, niveau, dureeHeures, prix, prerequis, programme, objectifs, modalites, statut
2. Add @Lob for long text fields (programme, description)
3. Create `FormationRepository` with custom queries
4. Implement findByCategorie, findByNiveau, searchByTitre
5. Add full-text search capabilities
6. Write unit tests

---

### Ticket #NEC-012: Backend - Formation Service & Controller
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S4

#### Technical Strategy
- Implement service layer for formation catalog
- Create REST endpoints with advanced search
- Add business logic for pricing and availability
- Implement categorization management

#### Development Steps
1. Create `FormationDTO` with nested program structure
2. Implement `FormationService` with search and filtering
3. Add price calculation logic
4. Create `FormationController` with search endpoints
5. Implement category-based filtering
6. Add niveau-based filtering
7. Add OpenAPI documentation
8. Write integration tests

---

### Ticket #NEC-013: Frontend - Formation Catalog Components
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S7

#### Technical Strategy
- Create formation catalog with cards display
- Implement advanced search and filtering
- Add category and niveau filters
- Create formation detail with program display
- Add wishlist/favorites functionality

#### Development Steps
1. Create `Formation` interface with program structure
2. Create `FormationService` with search API
3. Generate `FormationsCatalogComponent` with card layout
4. Implement search bar with autocomplete
5. Add category filter sidebar
6. Add niveau filter chips
7. Create `FormationDetailComponent` with program accordion
8. Add favorite button with localStorage
9. Write unit tests

---

## MODULE 4: GESTION DES SESSIONS

### Ticket #NEC-014: Backend - Session Entity & Repository
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S4

#### Technical Strategy
- Create Session entity linking Formation, Formateur, and Apprenants
- Implement repository with session scheduling queries
- Add capacity and availability management
- Set up many-to-many relationships

#### Development Steps
1. Create `Session` entity: id, formation (ManyToOne), formateur (ManyToOne), dateDebut, dateFin, lieu, capacite, statut, prixSession
2. Create `SessionApprenant` join entity for many-to-many
3. Create `SessionRepository` with date range queries
4. Implement findByDateRange, findByFormation, findByFormateur
5. Add availability check queries
6. Write unit tests

---

### Ticket #NEC-015: Backend - Formateur Entity & Repository
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S4

#### Technical Strategy
- Create Formateur entity with trainer information
- Implement repository with availability tracking
- Add expertise and specialization fields
- Set up relationship with sessions

#### Development Steps
1. Create `Formateur` entity: id, nom, prenom, email, telephone, specialites, bio, tauxHoraire, dispo
2. Create `FormateurRepository` with custom queries
3. Implement findBySpecialite, findAvailableFormateurs
4. Add expertise as @ElementCollection
5. Write unit tests

---

### Ticket #NEC-016: Backend - Session Service & Controller
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S4

#### Technical Strategy
- Implement session scheduling logic
- Create REST endpoints for session management
- Add capacity and availability validation
- Implement registration/deregistration logic

#### Development Steps
1. Create `SessionDTO` and `FormateurDTO`
2. Implement `SessionService` with scheduling logic
3. Add capacity validation
4. Implement apprenant registration (add/remove)
5. Create `SessionController` with endpoints
6. Add availability check endpoint
7. Implement session status management
8. Write integration tests

---

### Ticket #NEC-017: Frontend - Session Management Components
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S7

#### Technical Strategy
- Create session calendar/list view
- Implement session creation wizard
- Add formateur selection with availability
- Create participant management interface
- Add session status tracking

#### Development Steps
1. Create `Session` and `Formateur` interfaces
2. Create `SessionService` with API calls
3. Generate `SessionsListComponent` with calendar view
4. Create `SessionFormComponent` with multi-step wizard
5. Implement formateur selection with autocomplete
6. Add participant management with capacity check
7. Create session detail with participant list
8. Add status badges and filters
9. Write unit tests

---

## MODULE 5: GÉNÉRATION DOCUMENTAIRE

### Ticket #NEC-018: Backend - Document Generation Service
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S9

#### Technical Strategy
- Implement document generation using Apache POI or iText
- Create templates for conventions, convocations, attestations
- Add PDF generation capabilities
- Implement document storage and retrieval

#### Development Steps
1. Add Apache POI or iText dependency to pom.xml
2. Create `DocumentGenerationService`
3. Implement Convention generation template
4. Implement Convocation generation template
5. Implement Attestation generation template
6. Implement Certificat generation template
7. Add PDF export functionality
8. Create document storage service
9. Write unit tests

---

### Ticket #NEC-019: Backend - Document REST Controller
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S9

#### Technical Strategy
- Create REST endpoints for document generation
- Add document download functionality
- Implement document history tracking
- Add batch generation capabilities

#### Development Steps
1. Create `DocumentController` with generation endpoints
2. Implement GET /api/documents/convention/{sessionId}
3. Implement GET /api/documents/convocation/{apprenantId}
4. Implement GET /api/documents/attestation/{apprenantId}
5. Add document history tracking
6. Implement batch generation endpoint
7. Add OpenAPI documentation
8. Write integration tests

---

### Ticket #NEC-020: Frontend - Document Generation Interface
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S9

#### Technical Strategy
- Create document generation interface
- Add document preview functionality
- Implement batch document generation
- Add document download and print
- Create document history view

#### Development Steps
1. Create `DocumentService` with API calls
2. Generate `DocumentGenerationComponent`
3. Add document type selection
4. Implement document preview modal
5. Add download functionality with file handling
6. Create batch generation interface
7. Add document history list
8. Implement print functionality
9. Write unit tests

---

## MODULE 6: QUALIOPI

### Ticket #NEC-021: Backend - Qualiopi Evidence Management
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S10

#### Technical Strategy
- Create entity for Qualiopi evidence storage
- Implement file upload and storage
- Add evidence categorization by criteria
- Set up evidence expiration tracking

#### Development Steps
1. Create `PreuveQualiopi` entity: id, type, critere, date, fichier, statut, expiration
2. Create `PreuveQualiopiRepository`
3. Implement file upload service
4. Add evidence categorization by Qualiopi criteria
5. Create `QualiopiService` for evidence management
6. Add expiration tracking logic
7. Write unit tests

---

### Ticket #NEC-022: Backend - Evaluation & Satisfaction Tracking
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S10

#### Technical Strategy
- Create evaluation entity for feedback collection
- Implement satisfaction calculation logic
- Add evaluation questionnaire management
- Set up reporting for Qualiopi metrics

#### Development Steps
1. Create `Evaluation` entity: id, apprenant, session, note, commentaire, date
2. Create `Questionnaire` entity for evaluation questions
3. Create `EvaluationService` with calculation logic
4. Implement satisfaction rate calculation
5. Create reporting endpoints
6. Add evaluation history tracking
7. Write unit tests

---

### Ticket #NEC-023: Frontend - Qualiopi Dashboard
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S10

#### Technical Strategy
- Create Qualiopi evidence management interface
- Implement file upload for evidence
- Add evidence categorization and tracking
- Create evaluation and satisfaction interface
- Add Qualiopi compliance indicators

#### Development Steps
1. Create `PreuveQualiopi` interface
2. Create `QualiopiService` with API calls
3. Generate `QualiopiDashboardComponent`
4. Implement file upload with drag-and-drop
5. Add evidence categorization by criteria
6. Create evidence list with status tracking
7. Implement evaluation form
8. Add satisfaction rate display
9. Create compliance indicators
10. Write unit tests

---

## MODULE 7: TABLEAUX DE BORD

### Ticket #NEC-024: Backend - Dashboard Statistics Service
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S11

#### Technical Strategy
- Implement statistics calculation service
- Add CA estimation logic
- Calculate satisfaction rates
- Track hours delivered and learner counts
- Create aggregation queries for performance

#### Development Steps
1. Create `DashboardStatisticsService`
2. Implement CA estimation calculation
3. Implement satisfaction rate calculation
4. Implement hours delivered tracking
5. Implement learner count tracking
6. Add period-based filtering (month, quarter, year)
7. Create aggregation queries for performance metrics
8. Add caching for expensive calculations
9. Write unit tests

---

### Ticket #NEC-025: Backend - Dashboard REST Controller
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S11

#### Technical Strategy
- Create REST endpoints for dashboard data
- Add real-time statistics endpoints
- Implement historical data retrieval
- Add export functionality for reports

#### Development Steps
1. Create `DashboardController` with statistics endpoints
2. Implement GET /api/dashboard/statistics
3. Implement GET /api/dashboard/statistics/history
4. Add period parameter support
5. Implement export endpoint (CSV/Excel)
6. Add OpenAPI documentation
7. Write integration tests

---

### Ticket #NEC-026: Frontend - Dashboard Components
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S11

#### Technical Strategy
- Create main dashboard with KPI cards
- Implement charts for CA and satisfaction
- Add period filtering
- Create data tables for detailed views
- Add export functionality

#### Development Steps
1. Create `DashboardService` with API calls
2. Generate `DashboardComponent` with KPI cards
3. Implement CA chart with Chart.js or similar
4. Implement satisfaction rate chart
5. Add period filter (month/quarter/year)
6. Create detailed data tables
7. Implement export to CSV/Excel
8. Add responsive layout
9. Write unit tests

---

## MODULE 8: AUTHENTIFICATION & SÉCURITÉ

### Ticket #NEC-027: Backend - Keycloak Integration
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S3

#### Technical Strategy
- Configure Spring Security with Keycloak
- Set up JWT token validation
- Implement role-based access control
- Configure CORS for frontend
- Set up user role mapping

#### Development Steps
1. Add Keycloak dependencies to pom.xml
2. Configure application.properties for Keycloak
3. Create `SecurityConfig` class
4. Configure JWT token validation
5. Set up role-based access control
6. Configure CORS for Angular frontend
7. Create role hierarchy (ADMIN, FORMATEUR, APPRENANT)
8. Test authentication flow
9. Write security tests

---

### Ticket #NEC-028: Frontend - Keycloak Integration
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S6

#### Technical Strategy
- Configure Keycloak Angular library
- Implement authentication flow
- Add route guards for protected routes
- Implement token refresh logic
- Add logout functionality

#### Development Steps
1. Add keycloak-angular dependency
2. Configure Keycloak in app.config.ts
3. Create authentication service
4. Implement route guards (AuthGuard, RoleGuard)
5. Add login/logout functionality
6. Implement token refresh interceptor
7. Add user profile service
8. Test authentication flow
9. Write unit tests

---

## MODULE 9: API GATEWAY & SERVICE DISCOVERY

### Ticket #NEC-029: Backend - Eureka Server Configuration
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S5

#### Technical Strategy
- Set up Eureka server for service discovery
- Configure microservice registration
- Implement health checks
- Add service registry configuration

#### Development Steps
1. Create Eureka server project in necform-eureka-server
2. Configure application.yml for Eureka
3. Add @EnableEurekaServer annotation
4. Configure service registration
5. Set up health check endpoints
6. Test service registration
7. Write configuration documentation

---

### Ticket #NEC-030: Backend - API Gateway Configuration
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S5

#### Technical Strategy
- Set up Spring Cloud Gateway
- Configure route mapping
- Add load balancing
- Implement API gateway security
- Add rate limiting

#### Development Steps
1. Create Gateway project in necform-gateway
2. Configure Spring Cloud Gateway dependencies
3. Set up route mapping to microservices
4. Configure load balancing with Eureka
5. Add JWT token validation at gateway
6. Implement rate limiting
7. Add CORS configuration
8. Test gateway routing
9. Write configuration documentation

---

## MODULE 10: ASSISTANT IA (BONUS)

### Ticket #NEC-031: Backend - AI Service Setup
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S11

#### Technical Strategy
- Set up AI microservice
- Integrate with LLM API (OpenAI/HuggingFace)
- Implement RAG with vector database
- Add context management for Necform content

#### Development Steps
1. Create AI service project in necform-ai-service
2. Add dependencies for AI/ML libraries
3. Configure LLM API integration
4. Set up vector database (Pinecone/Milvus)
5. Implement document ingestion for Necform content
6. Create RAG pipeline
7. Add context management
8. Write API documentation

---

### Ticket #NEC-032: Backend - AI Chat Service
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S12

#### Technical Strategy
- Implement chat endpoint for AI assistant
- Add conversation history management
- Implement context-aware responses
- Add training catalog Q&A capabilities

#### Development Steps
1. Create `ChatService` for AI interactions
2. Implement chat REST endpoint
3. Add conversation history storage
4. Implement context retrieval from vector DB
5. Add training catalog query capabilities
6. Implement prerequisite and parcours recommendations
7. Add response filtering and safety
8. Write integration tests

---

### Ticket #NEC-033: Frontend - AI Chat Interface
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Moyenne
**Sprint**: S12

#### Technical Strategy
- Create chat interface for AI assistant
- Implement real-time messaging
- Add conversation history display
- Add suggested questions
- Implement typing indicators

#### Development Steps
1. Create `AIService` with chat API calls
2. Generate `AIChatComponent` with chat interface
3. Implement message input and display
4. Add real-time messaging with WebSocket or polling
5. Create conversation history sidebar
6. Add suggested questions chips
7. Implement typing indicator
8. Add markdown rendering for responses
9. Write unit tests

---

## MODULE 11: INFRASTRUCTURE & DÉPLOIEMENT

### Ticket #NEC-034: Docker - Container Configuration
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S5

#### Technical Strategy
- Create Dockerfiles for all services
- Configure Docker Compose orchestration
- Set up multi-container networking
- Add volume management for persistence
- Configure environment variables

#### Development Steps
1. Create Dockerfile for necform backend
2. Create Dockerfile for necform-frontend
3. Create Dockerfile for AI service
4. Update compose.yaml with all services
5. Configure service networking
6. Set up volume mounts for PostgreSQL
7. Add environment variable configuration
8. Test container orchestration
9. Write deployment documentation

---

### Ticket #NEC-035: Deployment - VPS/Azure Setup
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S13

#### Technical Strategy
- Set up VPS or Azure environment
- Configure SSL certificates
- Set up CI/CD pipeline
- Configure backup strategy
- Implement monitoring

#### Development Steps
1. Set up VPS or Azure VM
2. Install Docker and Docker Compose
3. Configure domain and SSL
4. Set up CI/CD pipeline (GitHub Actions)
5. Configure automated backups
6. Set up monitoring (Prometheus/Grafana)
7. Configure log aggregation
8. Test deployment pipeline
9. Write deployment guide

---

## MODULE 12: TESTS & QUALITÉ

### Ticket #NEC-036: Backend - Integration Tests
**Status**: Nouveau
**Developer**: Backend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S13

#### Technical Strategy
- Write comprehensive integration tests
- Test API endpoints with TestRestTemplate
- Test database operations with @DataJpaTest
- Test security configurations
- Add test coverage reporting

#### Development Steps
1. Set up test infrastructure
2. Write integration tests for all controllers
3. Write repository tests with test database
4. Write security tests
5. Add JaCoCo for coverage reporting
6. Configure test profiles
7. Run tests and ensure >80% coverage
8. Write test documentation

---

### Ticket #NEC-037: Frontend - Integration Tests
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S13

#### Technical Steps
1. Set up integration test framework
2. Write component integration tests
3. Write service integration tests
4. Test authentication flows
5. Add end-to-end tests with Cypress
6. Add test coverage reporting
7. Run tests and ensure >80% coverage
8. Write test documentation

---

## MODULE 13: DOCUMENTATION

### Ticket #NEC-038: Technical Documentation
**Status**: Nouveau
**Developer**: Full Stack Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S13

#### Technical Strategy
- Create comprehensive technical documentation
- Document API endpoints with Swagger
- Create architecture diagrams
- Document deployment procedures
- Create troubleshooting guide

#### Development Steps
1. Document system architecture
2. Create UML diagrams (class, sequence, component)
3. Document all API endpoints
4. Create database schema documentation
5. Document configuration files
6. Create deployment guide
7. Create troubleshooting guide
8. Document security best practices

---

### Ticket #NEC-039: User Documentation
**Status**: Nouveau
**Developer**: Frontend Dev
**Product Owner**: PO
**Priority**: Haute
**Sprint**: S13

#### Technical Strategy
- Create user guide for platform
- Document all user workflows
- Create video tutorials
- Add in-app help tooltips
- Create FAQ section

#### Development Steps
1. Create user guide document
2. Document each user workflow
3. Create screenshots and diagrams
4. Record video tutorials
5. Add help tooltips in application
6. Create FAQ section
7. Create quick start guide
8. Test documentation clarity

---

## RÉCAPITULATIF DES TICKETS

### Par Sprint
- **S1-S2**: Analyse et conception (pas de tickets dev)
- **S3**: NEC-001, NEC-002, NEC-003, NEC-007, NEC-008, NEC-011, NEC-027
- **S4**: NEC-009, NEC-012, NEC-014, NEC-015, NEC-016
- **S5**: NEC-029, NEC-030, NEC-034
- **S6**: NEC-004, NEC-005, NEC-006, NEC-028
- **S7**: NEC-010, NEC-013, NEC-017
- **S8**: (Frontend refinement and integration)
- **S9**: NEC-018, NEC-019, NEC-020
- **S10**: NEC-021, NEC-022, NEC-023
- **S11**: NEC-024, NEC-025, NEC-026, NEC-031
- **S12**: NEC-032, NEC-033
- **S13**: NEC-035, NEC-036, NEC-037, NEC-038, NEC-039

### Par Développeur
- **Backend Dev**: NEC-001, NEC-002, NEC-003, NEC-007, NEC-008, NEC-009, NEC-011, NEC-012, NEC-014, NEC-015, NEC-016, NEC-018, NEC-019, NEC-021, NEC-022, NEC-024, NEC-025, NEC-027, NEC-029, NEC-030, NEC-031, NEC-032, NEC-034, NEC-035, NEC-036, NEC-038
- **Frontend Dev**: NEC-004, NEC-005, NEC-006, NEC-010, NEC-013, NEC-017, NEC-020, NEC-023, NEC-026, NEC-028, NEC-033, NEC-037, NEC-039
- **Full Stack Dev**: NEC-038

### Total Tickets: 39
- Backend: 25 tickets
- Frontend: 13 tickets
- Infrastructure: 1 ticket

---

## STATUS TRACKING

### Tickets à faire (Nouveau): 39
### Tickets en cours: 0
### Tickets terminés: 0

---

## PRIORITÉS

### Haute Priorité: 28 tickets
### Moyenne Priorité: 11 tickets

---

## DÉPENDANCES ENTRE TICKETS

### Backend Dependencies
- NEC-002 depends on NEC-001
- NEC-003 depends on NEC-002
- NEC-009 depends on NEC-007, NEC-008
- NEC-012 depends on NEC-011
- NEC-016 depends on NEC-014, NEC-015
- NEC-019 depends on NEC-018
- NEC-022 depends on NEC-021
- NEC-025 depends on NEC-024
- NEC-032 depends on NEC-031

### Frontend Dependencies
- NEC-005 depends on NEC-004
- NEC-006 depends on NEC-004
- NEC-010 depends on NEC-009 (backend)
- NEC-013 depends on NEC-012 (backend)
- NEC-017 depends on NEC-016 (backend)
- NEC-020 depends on NEC-019 (backend)
- NEC-023 depends on NEC-022 (backend)
- NEC-026 depends on NEC-025 (backend)
- NEC-028 depends on NEC-027 (backend)
- NEC-033 depends on NEC-032 (backend)

---

## LIVRABLES ATTENDUS

1. **Cahier des charges** (S1-S2)
2. **Diagrammes UML** (S1-S2)
3. **Code source Git** (S3-S12)
4. **Base de données** (S3-S5)
5. **Documentation technique** (S13)
6. **Guide utilisateur** (S13)
7. **Déploiement Docker** (S13)
8. **Présentation finale** (S13)

---

## COMPÉTENCES MOBILISÉES

- Java, Spring Boot, Angular, SQL
- Docker, Architecture REST
- Sécurité, IA générative
- Gestion de projet Agile