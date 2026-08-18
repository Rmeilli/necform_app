# Necform Project Development Guide

## Purpose
Master skill for the Necform full-stack project. Provides high-level guidance and coordinates specialized skills for Spring Boot backend, Angular frontend, and Docker infrastructure development.

## Project Overview
**Necform** is a microservices-based enterprise application with the following technology stack:

### Technology Stack
- **Backend**: Spring Boot 4.1.0 (Java 17)
- **Frontend**: Angular 22.0.0 (TypeScript)
- **Database**: PostgreSQL 16
- **Authentication**: Keycloak 26.2
- **Infrastructure**: Docker Compose
- **Architecture**: Microservices with API Gateway
- **Service Discovery**: Eureka Server
- **Documentation**: SpringDoc OpenAPI (Swagger)

### Architecture
```
┌─────────────┐     ┌──────────────┐     ┌─────────────┐
│   Angular   │────▶│ API Gateway  │────▶│ Microservices│
│  Frontend   │     │   (Spring)   │     │  (Spring)   │
└─────────────┘     └──────────────┘     └─────────────┘
                           │                       │
                           ▼                       ▼
                    ┌──────────────┐      ┌─────────────┐
                    │   Keycloak   │      │ PostgreSQL  │
                    │  (Auth)      │      │  (Database) │
                    └──────────────┘      └─────────────┘
```

## Project Structure
```
Necform/
├── necform/                    # Main Spring Boot backend
├── necform-front/             # Angular frontend
├── necform-gateway/          # API Gateway service
├── necform-eureka-server/    # Service discovery
├── necform-ai-service/       # AI microservice
├── compose.yaml              # Docker orchestration
├── .devin/                   # Devin skills
│   └── skills/
│       ├── project-guide/    # This skill
│       ├── springboot-backend/
│       ├── angular-frontend/
│       ├── docker-orchestration/
│       └── fullstack-workflow/
└── [Documentation files]
```

## Specialized Skills

### 1. Git Workflow (`git-workflow`)
**Use for**: Git branch management and pull request process
- Feature branch creation for each ticket
- Commit message conventions
- Pull request creation and review
- Branch management and cleanup
- Conflict resolution
- Release and hotfix workflows

**Invoke when**: Starting any development ticket or managing Git operations

### 2. CI/CD Setup (`ci-cd-setup`)
**Use for**: Continuous integration and deployment configuration
- Branch name validation
- Commit message validation
- Backend quality checks (tests, coverage, security)
- Frontend quality checks (linting, tests, build)
- Docker validation
- Pull request quality validation
- Automated code quality enforcement

**Invoke when**: Understanding CI/CD processes, fixing CI failures, or configuring quality gates

### 3. Spring Boot Backend (`springboot-backend`)
**Use for**: Backend-specific development tasks
- Entity and repository development
- REST API creation
- Security configuration
- Service layer implementation
- Backend testing

**Invoke when**: Working on Spring Boot code, database operations, or backend APIs

### 4. Angular Frontend (`angular-frontend`)
**Use for**: Frontend-specific development tasks
- Component development
- Service creation
- Authentication integration
- Routing and navigation
- UI/UX implementation
- Frontend testing

**Invoke when**: Working on Angular components, services, or UI

### 5. Docker Orchestration (`docker-orchestration`)
**Use for**: Infrastructure and containerization tasks
- Container management
- Database containerization
- Keycloak configuration
- Microservice deployment
- Volume and network management
- Infrastructure troubleshooting

**Invoke when**: Working with Docker, containers, or infrastructure

### 6. Full-Stack Workflow (`fullstack-workflow`)
**Use for**: Coordinating full-stack development
- Feature implementation across backend and frontend
- Integration between services
- End-to-end testing
- Deployment orchestration
- Cross-cutting concerns

**Invoke when**: Implementing features that require both backend and frontend changes

### 7. Necform Tickets (`necform-tickets`)
**Use for**: Project ticket management and development guidance
- 39 development tickets organized by module
- Technical strategies for each ticket
- Development steps for backend and frontend
- Git workflow integration for each ticket
- Sprint planning and dependencies

**Invoke when**: Planning development work or needing ticket-specific guidance

## Development Scenarios

### Scenario 1: Starting a New Development Ticket
1. **Invoke `git-workflow`** to create feature branch
2. **Invoke `necform-tickets`** to understand ticket requirements
3. **Invoke appropriate specialized skill** for implementation
4. **Invoke `git-workflow`** for commit and PR process
5. **CI/CD automatically validates** branch name, commits, and code quality

### Scenario 2: Adding a New Feature
1. **Invoke `git-workflow`** to create feature branch
2. **Invoke `fullstack-workflow`** for overall guidance
3. **Invoke `springboot-backend`** for backend implementation
4. **Invoke `angular-frontend`** for frontend implementation
5. **Invoke `docker-orchestration`** if infrastructure changes needed
6. **Invoke `git-workflow`** for PR creation and review
7. **CI/CD automatically validates** all changes and quality gates

### Scenario 3: Backend API Development
1. **Invoke `git-workflow`** to create feature branch
2. **Invoke `springboot-backend`** for API implementation
3. **Invoke `fullstack-workflow`** for integration testing
4. **Invoke `git-workflow`** for commit and PR process
5. **CI/CD backend checks** automatically validate code quality, tests, and security

### Scenario 4: Frontend Component Development
1. **Invoke `git-workflow`** to create feature branch
2. **Invoke `angular-frontend`** for component implementation
3. **Invoke `fullstack-workflow`** for API integration
4. **Invoke `git-workflow`** for commit and PR process
5. **CI/CD frontend checks** automatically validate linting, tests, and build

### Scenario 5: Infrastructure Setup
1. **Invoke `git-workflow`** to create feature branch
2. **Invoke `docker-orchestration`** for container setup
3. **Invoke `springboot-backend`** for backend configuration
4. **Invoke `angular-frontend`** for frontend configuration
5. **Invoke `git-workflow`** for commit and PR process
6. **CI/CD Docker validation** automatically validates Docker configuration

### Scenario 6: Debugging CI Failures
1. **Invoke `ci-cd-setup`** to understand CI failure
2. **Invoke appropriate specialized skill** to fix code issues
3. **Invoke `git-workflow`** if branch/commit fixes needed
4. **Push changes** and let CI re-validate

### Scenario 7: Debugging Issues
1. **Invoke appropriate specialized skill** based on issue location
2. **Invoke `fullstack-workflow`** for integration issues
3. **Invoke `git-workflow`** if branch management needed
4. **Invoke `ci-cd-setup`** if CI-related issues

## Quick Start

### Initial Setup
```bash
# Clone repository and navigate to project
cd C:\Users\User\Downloads\Necform

# Start infrastructure (PostgreSQL + Keycloak)
docker-compose up -d postgres keycloak

# Start backend
cd necform
.\mvnw spring-boot:run

# Start frontend (new terminal)
cd necform-front
npm start
```

### Access Points
- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Keycloak Admin**: http://localhost:8081/admin (admin/admin)
- **PostgreSQL**: localhost:5432 (necform/necform)

## Common Development Tasks

### Task: Create New Entity
1. **Invoke `springboot-backend`** for entity, repository, service, controller
2. **Invoke `angular-frontend`** for models, services, components
3. **Invoke `fullstack-workflow`** for integration testing

### Task: Add Authentication
1. **Invoke `springboot-backend`** for security configuration
2. **Invoke `angular-frontend`** for Keycloak integration
3. **Invoke `docker-orchestration`** for Keycloak setup

### Task: Deploy to Production
1. **Invoke `docker-orchestration`** for container deployment
2. **Invoke `fullstack-workflow`** for deployment orchestration

### Task: Debug API Issues
1. **Invoke `springboot-backend`** for backend debugging
2. **Invoke `angular-frontend`** for frontend debugging
3. **Invoke `docker-orchestration`** for infrastructure checks

## Project Configuration

### Backend Configuration
- **Build Tool**: Maven
- **Java Version**: 17
- **Spring Boot**: 4.1.0
- **Database**: PostgreSQL 16
- **ORM**: Hibernate/JPA
- **Security**: Spring Security + Keycloak
- **API Documentation**: SpringDoc OpenAPI

### Frontend Configuration
- **Framework**: Angular 22.0.0
- **Language**: TypeScript
- **Styling**: Bootstrap 5.3.8
- **HTTP Client**: Axios
- **Authentication**: Keycloak Angular
- **Build Tool**: Angular CLI

### Infrastructure Configuration
- **Containerization**: Docker
- **Orchestration**: Docker Compose
- **Database**: PostgreSQL 16 container
- **Authentication**: Keycloak 26.2 container
- **Network**: Bridge network with service discovery

## Development Guidelines

### Code Standards
- Follow Java coding conventions for backend
- Follow Angular style guide for frontend
- Use meaningful variable and function names
- Add proper error handling
- Write tests for new features
- Document complex logic

### Git Workflow
- Use feature branches for new features
- Create descriptive commit messages
- Write pull request descriptions
- Code review before merging
- Keep commit history clean

### Security Practices
- Never commit secrets or credentials
- Use environment variables for sensitive data
- Implement proper authentication and authorization
- Validate all user inputs
- Keep dependencies updated
- Regular security audits

### Performance Considerations
- Use database indexes appropriately
- Implement caching where needed
- Optimize database queries
- Use lazy loading for JPA relationships
- Implement pagination for large datasets
- Monitor application performance

## Testing Strategy

### Backend Testing
- Unit tests for services and repositories
- Integration tests for controllers
- Security tests for authorization
- Contract tests for API validation

### Frontend Testing
- Unit tests for components
- Service tests with mocked HTTP
- Guard tests for authentication
- Integration tests for user flows

### Integration Testing
- End-to-end user journey tests
- API contract tests
- Authentication flow tests
- Data consistency tests

## Deployment Strategy

### Development Environment
- Local PostgreSQL and Keycloak via Docker
- Spring Boot services run locally
- Angular development server
- Hot reload enabled

### Production Environment
- All services in Docker containers
- PostgreSQL with persistent volumes
- Keycloak with production configuration
- API Gateway for routing
- Eureka for service discovery
- Health checks and monitoring

## Troubleshooting

### Common Issues
1. **Database connection**: Check PostgreSQL container status
2. **Authentication failures**: Verify Keycloak configuration
3. **API errors**: Check backend logs and Swagger UI
4. **Frontend errors**: Check browser console and network tab
5. **Container issues**: Check Docker logs and container status

### Getting Help
1. Check relevant specialized skill documentation
2. Review application logs
3. Check Docker container status
4. Verify configuration files
5. Test with Swagger UI for API issues

## Documentation

### Project Documentation
- **Project Overview**: This file
- **Backend Guide**: `springboot-backend` skill
- **Frontend Guide**: `angular-frontend` skill
- **Infrastructure Guide**: `docker-orchestration` skill
- **Workflow Guide**: `fullstack-workflow` skill

### External Documentation
- Spring Boot: https://spring.io/projects/spring-boot
- Angular: https://angular.io/docs
- PostgreSQL: https://www.postgresql.org/docs/
- Keycloak: https://www.keycloak.org/documentation
- Docker: https://docs.docker.com/

## Key Resources

### Essential Files
- **Backend Config**: `necform/src/main/resources/application.properties`
- **Frontend Config**: `necform-front/package.json`
- **Docker Config**: `compose.yaml`
- **Maven Config**: `necform/pom.xml`
- **Angular Config**: `necform-front/angular.json`

### Important Directories
- **Backend Controllers**: `necform/src/main/java/org/sid/necform/controller/`
- **Frontend Components**: `necform-front/src/app/features/`
- **Backend Services**: `necform/src/main/java/org/sid/necform/service/`
- **Frontend Services**: `necform-front/src/app/core/services/`

## Skill Invocation Guide

### When to Invoke This Skill
- Project overview and architecture questions
- High-level development guidance
- Coordinating multiple specialized skills
- Understanding project structure
- General development workflow questions

### When to Invoke Specialized Skills
- **springboot-backend**: Backend-specific development
- **angular-frontend**: Frontend-specific development
- **docker-orchestration**: Infrastructure and containerization
- **fullstack-workflow**: Cross-stack feature implementation

## Best Practices

### Development Workflow
1. Understand requirements completely
2. Design solution before implementation
3. Use appropriate specialized skills
4. Test thoroughly at each layer
5. Document complex decisions
6. Review code before committing

### Collaboration
- Communicate changes effectively
- Use meaningful commit messages
- Create descriptive pull requests
- Review code thoroughly
- Share knowledge with team

### Continuous Improvement
- Learn from mistakes
- Refactor code regularly
- Update documentation
- Share improvements
- Stay current with technologies

## Project-Specific Patterns

### Backend Patterns
- DTO pattern for API contracts
- Service layer for business logic
- Repository pattern for data access
- Exception handling with @ControllerAdvice
- Security with method-level annotations

### Frontend Patterns
- Standalone components
- Service-based state management
- Reactive forms with validation
- Route guards for authentication
- HTTP interceptors for API calls

### Infrastructure Patterns
- Multi-stage Docker builds
- Health checks for all services
- Volume mounts for persistence
- Environment-based configuration
- Service discovery with Eureka

## Quick Commands Reference

### Backend
```bash
cd necform
.\mvnw spring-boot:run          # Run application
.\mvnw clean install            # Build project
.\mvnw test                     # Run tests
```

### Frontend
```bash
cd necform-front
npm start                       # Start dev server
npm run build                   # Build for production
npm test                        # Run tests
```

### Docker
```bash
docker-compose up -d            # Start all services
docker-compose down             # Stop all services
docker-compose logs -f          # View logs
docker-compose ps               # Check status
```

## Important Notes
- This project uses Spring Boot 4.1.0 (latest version)
- Angular 22.0.0 is used for the frontend
- PostgreSQL 16 is the database
- Keycloak 26.2 for authentication
- Always backup database before schema changes
- Test authentication flows thoroughly
- Keep API contracts stable
- Monitor container resource usage
- Use proper git branching strategy
- Review security configurations regularly

## Next Steps
1. **For backend development**: Invoke `springboot-backend` skill
2. **For frontend development**: Invoke `angular-frontend` skill
3. **For infrastructure**: Invoke `docker-orchestration` skill
4. **For full-stack features**: Invoke `fullstack-workflow` skill
5. **For project overview**: Continue using this skill

## Contact & Support
- Check project documentation in root directory
- Review specialized skill documentation
- Check Spring Boot, Angular, and Docker official docs
- Review existing code for patterns and conventions