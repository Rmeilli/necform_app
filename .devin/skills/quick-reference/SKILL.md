# Necform Project Quick Reference

## Purpose
Quick reference guide for the Necform project skills and their usage in the development workflow.

## Available Skills

### 1. project-guide (Master Skill)
**Location**: `.devin/skills/project-guide/SKILL.md`
**Purpose**: Project overview and skill coordination
**When to use**: First point of contact for project questions, understanding architecture, or coordinating multiple skills

### 2. git-workflow (Git Management)
**Location**: `.devin/skills/git-workflow/SKILL.md`
**Purpose**: Git branch management, commit conventions, and PR process
**When to use**: Starting any development ticket, managing branches, creating pull requests

### 3. ci-cd-setup (CI/CD Configuration)
**Location**: `.devin/skills/ci-cd-setup/SKILL.md`
**Purpose**: Continuous integration and deployment with automated quality checks
**When to use**: Understanding CI processes, fixing CI failures, code quality enforcement

### 4. necform-tickets (Ticket Management)
**Location**: `.devin/skills/necform-tickets/SKILL.md`
**Purpose**: 39 development tickets with technical strategies and Git workflow integration
**When to use**: Planning development work, understanding ticket requirements, checking dependencies

### 5. springboot-backend (Backend Development)
**Location**: `.devin/skills/springboot-backend/SKILL.md`
**Purpose**: Spring Boot backend development guidance
**When to use**: Working on backend code, database operations, REST APIs, security

### 6. angular-frontend (Frontend Development)
**Location**: `.devin/skills/angular-frontend/SKILL.md`
**Purpose**: Angular frontend development guidance
**When to use**: Working on frontend components, services, UI, authentication

### 7. docker-orchestration (Infrastructure)
**Location**: `.devin/skills/docker-orchestration/SKILL.md`
**Purpose**: Docker containerization and infrastructure management
**When to use**: Working with containers, deployment, PostgreSQL, Keycloak

### 8. fullstack-workflow (Integration)
**Location**: `.devin/skills/fullstack-workflow/SKILL.md`
**Purpose**: Full-stack development coordination and integration
**When to use**: Features requiring both backend and frontend changes, integration testing

## Standard Development Workflow

### For Any Development Ticket:
1. **Invoke `git-workflow`** → Create feature branch
2. **Invoke `necform-tickets`** → Understand ticket requirements
3. **Invoke specialized skill** → Implement changes
4. **Invoke `git-workflow`** → Commit and create PR
5. **CI/CD automatically validates** → Branch name, commits, code quality
6. **Fix any CI failures** → Use `ci-cd-setup` skill for guidance
7. **Update ticket status** → Mark as completed after merge

### Backend Ticket Example (NEC-001):
```bash
# 1. Git workflow
git checkout develop && git pull
git checkout -b feature/NEC-001-apprenant-entity-repository

# 2. Implementation (using springboot-backend skill)
cd necform
# Create entity, repository, tests...

# 3. Git commit
git add .
git commit -m "NEC-001: Create Apprenant entity and repository"
git push origin feature/NEC-001-apprenant-entity-repository

# 4. Create PR to develop branch
```

### Frontend Ticket Example (NEC-004):
```bash
# 1. Git workflow
git checkout develop && git pull
git checkout -b feature/NEC-004-apprenant-list-component

# 2. Implementation (using angular-frontend skill)
cd necform-front
# Create component, service, tests...

# 3. Git commit
git add .
git commit -m "NEC-004: Implement apprenant list component"
git push origin feature/NEC-004-apprenant-list-component

# 4. Create PR to develop branch
```

## Project Phases and Skills

### Phase 1: Setup & Analysis (S1-S2)
- **Skills**: project-guide, git-workflow
- **Tasks**: Project setup, requirements analysis, UML diagrams

### Phase 2: Backend Development (S3-S5)
- **Skills**: git-workflow, springboot-backend, docker-orchestration, necform-tickets
- **Tickets**: NEC-001 to NEC-016, NEC-027, NEC-029, NEC-030, NEC-034
- **Focus**: Entities, repositories, services, controllers, security

### Phase 3: Frontend Development (S6-S8)
- **Skills**: git-workflow, angular-frontend, fullstack-workflow, necform-tickets
- **Tickets**: NEC-004 to NEC-006, NEC-010, NEC-013, NEC-017, NEC-028
- **Focus**: Components, services, authentication, UI

### Phase 4: Document Generation (S9-S10)
- **Skills**: git-workflow, springboot-backend, angular-frontend, necform-tickets
- **Tickets**: NEC-018 to NEC-020, NEC-021 to NEC-023
- **Focus**: PDF generation, Qualiopi compliance, evaluations

### Phase 5: Dashboard & AI (S11-S12)
- **Skills**: git-workflow, springboot-backend, angular-frontend, necform-tickets
- **Tickets**: NEC-024 to NEC-026, NEC-031 to NEC-033
- **Focus**: Statistics, charts, AI assistant, chat interface

### Phase 6: Testing & Deployment (S13)
- **Skills**: git-workflow, docker-orchestration, fullstack-workflow, necform-tickets
- **Tickets**: NEC-035 to NEC-039
- **Focus**: Integration tests, deployment, documentation, presentation

## Quick Commands Reference

### Git Commands
```bash
# Start new ticket
git checkout develop && git pull && git checkout -b feature/NEC-XXX-name

# Commit changes
git add . && git commit -m "NEC-XXX: Description"

# Push and create PR
git push -u origin feature/NEC-XXX-name
```

### Backend Commands
```bash
cd necform
.\mvnw spring-boot:run          # Run application
.\mvnw test                     # Run tests
.\mvnw clean package           # Build project
```

### Frontend Commands
```bash
cd necform-front
npm start                       # Start dev server
npm test                        # Run tests
npm run build                   # Build for production
```

### Docker Commands
```bash
docker-compose up -d            # Start all services
docker-compose down             # Stop all services
docker-compose logs -f          # View logs
```

## Key Access Points

### Application URLs
- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Keycloak Admin**: http://localhost:8081/admin (admin/admin)
- **PostgreSQL**: localhost:5432 (necform/necform)

### Project Structure
```
Necform/
├── necform/                    # Spring Boot backend
├── necform-front/             # Angular frontend
├── necform-gateway/          # API Gateway
├── necform-eureka-server/    # Service Discovery
├── necform-ai-service/       # AI Microservice
├── compose.yaml              # Docker orchestration
├── .github/                  # GitHub CI/CD workflows
│   └── workflows/
│       ├── branch-name-validation.yml
│       ├── commit-message-validation.yml
│       ├── backend-ci.yml
│       ├── frontend-ci.yml
│       ├── docker-validation.yml
│       └── pr-validation.yml
└── .devin/                   # Devin skills
    └── skills/
        ├── quick-reference/   # This file
        ├── project-guide/
        ├── git-workflow/
        ├── ci-cd-setup/      # CI/CD configuration
        ├── necform-tickets/
        ├── springboot-backend/
        ├── angular-frontend/
        ├── docker-orchestration/
        └── fullstack-workflow/
```

## Ticket Summary

### Total Tickets: 39
- **Backend**: 25 tickets (NEC-001 to NEC-003, NEC-007 to NEC-016, NEC-018 to NEC-019, NEC-021 to NEC-022, NEC-024 to NEC-025, NEC-027, NEC-029 to NEC-032, NEC-034 to NEC-036, NEC-038)
- **Frontend**: 13 tickets (NEC-004 to NEC-006, NEC-010, NEC-013, NEC-017, NEC-020, NEC-023, NEC-026, NEC-028, NEC-033, NEC-037, NEC-039)
- **Infrastructure**: 1 ticket (NEC-035)

### Sprint Distribution
- **S3**: 7 tickets (Backend setup and core entities)
- **S4**: 5 tickets (Service and controller development)
- **S5**: 3 tickets (Infrastructure and gateway)
- **S6**: 4 tickets (Frontend authentication and core UI)
- **S7**: 3 tickets (Frontend feature modules)
- **S8**: Frontend refinement
- **S9**: 3 tickets (Document generation)
- **S10**: 3 tickets (Qualiopi and evaluations)
- **S11**: 4 tickets (Dashboard and AI setup)
- **S12**: 2 tickets (AI chat interface)
- **S13**: 5 tickets (Testing, deployment, documentation)

## Common Issues and Solutions

### Git Issues
- **Merge conflicts**: Use `git-workflow` skill for resolution
- **Wrong branch**: Delete and recreate with correct name
- **Push rejected**: Pull latest develop and rebase

### Backend Issues
- **Database connection**: Check PostgreSQL container status
- **Port conflicts**: Verify no other service using port 8080
- **Build failures**: Check Maven dependencies and Java version

### Frontend Issues
- **API connection**: Verify backend is running and CORS configured
- **Build errors**: Check Node.js version and dependencies
- **Routing issues**: Verify Angular Router configuration

### Docker Issues
- **Container won't start**: Check logs with `docker-compose logs`
- **Volume issues**: Check volume permissions and disk space
- **Network issues**: Verify Docker network configuration

## Skill Invocation Guide

### Starting New Work
1. **project-guide** → Understand project context
2. **git-workflow** → Set up development branch
3. **necform-tickets** → Find relevant ticket
4. **Specialized skill** → Implement changes
5. **git-workflow** → Complete PR process
6. **CI/CD automatically validates** → All quality checks
7. **ci-cd-setup** → Fix any CI failures if needed

### Debugging Issues
1. **project-guide** → Understand system architecture
2. **Specialized skill** → Debug specific component
3. **fullstack-workflow** → Debug integration issues
4. **docker-orchestration** → Check infrastructure
5. **ci-cd-setup** → Debug CI/CD failures

### Planning Features
1. **necform-tickets** → Review ticket requirements
2. **project-guide** → Understand architectural impact
3. **git-workflow** → Plan branch strategy
4. **Specialized skills** → Plan technical approach
5. **ci-cd-setup** → Understand CI/CD requirements

## Best Practices

### Development
- Always create feature branch for each ticket
- Follow commit message convention (NEC-XXX: description)
- Write tests for all new functionality
- Review code before creating PR
- Update ticket status after merge

### Git Management
- Pull latest develop before starting work
- Keep branches focused on single ticket
- Delete merged branches promptly
- Resolve conflicts immediately
- Never commit directly to main/develop

### Code Quality
- Follow project coding standards
- Add meaningful comments for complex logic
- Keep functions small and focused
- Use proper error handling
- Update documentation for API changes

### Collaboration
- Communicate changes in PR descriptions
- Request review from appropriate team members
- Respond to review feedback promptly
- Share knowledge with team
- Document decisions and trade-offs

## Emergency Contacts

### For Project Issues
- Check `project-guide` skill for architecture questions
- Review `necform-tickets` for ticket-specific guidance
- Consult appropriate specialized skill for technical issues

### For Git Issues
- Use `git-workflow` skill for branch management
- Check Git documentation for advanced operations
- Contact team lead for critical repository issues

### For Infrastructure Issues
- Use `docker-orchestration` skill for container issues
- Check Docker documentation for advanced operations
- Review infrastructure documentation for deployment issues

## Next Steps

### New to Project?
1. Start with `project-guide` skill
2. Review `necform-tickets` for understanding scope
3. Set up development environment using `docker-orchestration`
4. Learn `git-workflow` for branch management

### Starting Development?
1. Find ticket in `necform-tickets`
2. Use `git-workflow` to create branch
3. Implement using specialized skills
4. Complete with PR process

### Need Help?
1. Check relevant skill documentation
2. Review project documentation in root directory
3. Consult with team members
4. Check official technology documentation

---

**Remember**: Each skill is designed to be used in combination with others. Start with `project-guide` for overall context, then use specialized skills for specific tasks, and `git-workflow` for all version control operations.