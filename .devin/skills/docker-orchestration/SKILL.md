# Docker Orchestration & Infrastructure

## Purpose
High-level skill for Docker containerization and orchestration in the Necform project. Handles PostgreSQL, Keycloak, and microservice containerization.

## Project Structure
- **Project Root**: `C:\Users\User\Downloads\Necform`
- **Docker Compose**: `compose.yaml`
- **Services**: PostgreSQL 16, Keycloak 26.2, Spring Boot microservices
- **Architecture**: Multi-container microservices with API Gateway

## Capabilities

### Container Management
- Start/stop Docker containers using Docker Compose
- Build Docker images for Spring Boot services
- Configure container networking
- Manage container volumes and persistence
- Handle container health checks
- Monitor container logs

### Database Containerization
- Configure PostgreSQL containers
- Set up database initialization
- Manage database volumes
- Configure database backups
- Handle database migrations in containers
- Set up database networking

### Authentication Service
- Configure Keycloak containers
- Set up Keycloak with PostgreSQL backend
- Configure Keycloak realms and clients
- Manage Keycloak admin credentials
- Handle Keycloak health checks
- Configure Keycloak networking

### Microservice Containerization
- Create Dockerfiles for Spring Boot services
- Configure multi-stage builds
- Set up service discovery (Eureka)
- Configure API Gateway
- Handle inter-service communication
- Implement container orchestration

### Network Configuration
- Configure Docker networks
- Set up service discovery
- Handle port mapping
- Configure load balancing
- Manage network security
- Implement service mesh if needed

### Volume Management
- Configure persistent volumes
- Set up volume backups
- Handle volume migration
- Manage volume permissions
- Configure volume drivers
- Implement volume snapshots

## Common Commands

### Docker Compose Operations
```bash
# Navigate to project root
cd C:\Users\User\Downloads\Necform

# Start all services
docker-compose up -d

# Start specific service
docker-compose up -d postgres

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v

# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f postgres

# Rebuild services
docker-compose up -d --build

# Scale services
docker-compose up -d --scale necform=3
```

### Container Management
```bash
# List running containers
docker ps

# List all containers
docker ps -a

# View container logs
docker logs necform-postgres

# Execute command in container
docker exec -it necform-postgres psql -U necform -d necform

# Copy files to container
docker cp local_file.txt necform-postgres:/path/in/container/

# Copy files from container
docker cp necform-postgres:/path/in/container/file.txt local_file.txt

# View container stats
docker stats necform-postgres
```

### Image Management
```bash
# List images
docker images

# Build image
docker build -t necform-backend:latest .

# Remove image
docker rmi necform-backend:latest

# Prune unused images
docker image prune -a
```

### Volume Management
```bash
# List volumes
docker volume ls

# Inspect volume
docker volume inspect necform_necform_pgdata

# Remove volume
docker volume rm necform_necform_pgdata

# Backup volume
docker run --rm -v necform_necform_pgdata:/data -v $(pwd):/backup alpine tar czf /backup/necform_pgdata_backup.tar.gz /data
```

## Configuration Files

### Docker Compose (compose.yaml)
```yaml
services:
  postgres:
    image: postgres:16
    container_name: necform-postgres
    restart: always
    environment:
      POSTGRES_DB: necform
      POSTGRES_USER: necform
      POSTGRES_PASSWORD: necform
    ports:
      - "5432:5432"
    volumes:
      - necform_pgdata:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U necform"]
      interval: 10s
      timeout: 5s
      retries: 5

  keycloak:
    image: quay.io/keycloak/keycloak:26.2
    container_name: necform-keycloak
    command: start-dev
    restart: always
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
      KC_DB: postgres
      KC_DB_URL: jdbc:postgresql://postgres:5432/necform
      KC_DB_USERNAME: necform
      KC_DB_PASSWORD: necform
    ports:
      - "8081:8080"
    depends_on:
      postgres:
        condition: service_healthy

volumes:
  necform_pgdata:
```

### Spring Boot Dockerfile
```dockerfile
# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Network Architecture

### Service Communication
- **PostgreSQL**: `postgres:5432` (internal), `localhost:5432` (external)
- **Keycloak**: `keycloak:8080` (internal), `localhost:8081` (external)
- **API Gateway**: `gateway:8080` (internal), `localhost:8080` (external)
- **Microservices**: Internal communication via service names

### Network Configuration
- Default bridge network for services
- Service discovery via container names
- Port mapping for external access
- Health checks for service dependencies

## Database Operations

### PostgreSQL Container Access
```bash
# Connect to PostgreSQL
docker exec -it necform-postgres psql -U necform -d necform

# Create database
CREATE DATABASE necform;

# List databases
\l

# Connect to database
\c necform

# List tables
\dt

# Describe table
\d table_name

# Export database
docker exec necform-postgres pg_dump -U necform necform > backup.sql

# Import database
docker exec -i necform-postgres psql -U necform necform < backup.sql
```

## Keycloak Configuration

### Admin Console Access
- URL: `http://localhost:8081/admin`
- Username: `admin`
- Password: `admin`

### Keycloak Setup Steps
1. Access admin console
2. Create new realm (e.g., `necform`)
3. Create clients for frontend/backend
4. Configure client roles
5. Set up user roles and groups
6. Configure realm settings

### Keycloak Client Configuration
- **Frontend Client**: Public client with redirect URIs
- **Backend Client**: Confidential client with secret
- **Valid Redirect URIs**: Frontend URLs
- **Web Origins**: CORS configuration

## Microservice Deployment

### Service Deployment Order
1. Start PostgreSQL (database layer)
2. Start Keycloak (authentication layer)
3. Start Eureka Server (service discovery)
4. Start microservices (application layer)
5. Start API Gateway (entry point)

### Service Health Checks
```yaml
healthcheck:
  test: ["CMD", "curl", "-f", "http://localhost:8080/actuator/health"]
  interval: 30s
  timeout: 10s
  retries: 3
  start_period: 40s
```

## Development Workflow

### Git Setup for Infrastructure Changes
```bash
# Create feature branch for infrastructure ticket
git checkout develop
git pull origin develop
git checkout -b feature/NEC-XXX-infrastructure-description
```

### Local Development
```bash
# Start infrastructure only
docker-compose up -d postgres keycloak

# Run Spring Boot services locally
cd necform
.\mvnw spring-boot:run

# Run Angular frontend locally
cd necform-front
npm start
```

### Full Container Deployment
```bash
# Build Spring Boot services
cd necform
.\mvnw clean package

# Build Docker images
docker build -t necform-backend:latest .

# Start all services
docker-compose up -d
```

### Git Commit for Infrastructure Changes
```bash
# After testing infrastructure changes
git add .
git commit -m "NEC-XXX: Infrastructure change description"
git push origin feature/NEC-XXX-infrastructure-description
# Create PR to develop branch
```

### Troubleshooting
```bash
# Check container status
docker-compose ps

# View service logs
docker-compose logs -f [service_name]

# Restart specific service
docker-compose restart [service_name]

# Rebuild and restart
docker-compose up -d --build [service_name]

# Check container resources
docker stats
```

## Backup & Recovery

### Database Backup
```bash
# Backup PostgreSQL volume
docker run --rm -v necform_necform_pgdata:/data -v %cd%:/backup alpine tar czf /backup/pgdata_backup.tar.gz /data

# Backup specific database
docker exec necform-postgres pg_dump -U necform necform > necform_backup.sql
```

### Database Recovery
```bash
# Restore PostgreSQL volume
docker run --rm -v necform_necform_pgdata:/data -v %cd%:/backup alpine tar xzf /backup/pgdata_backup.tar.gz -C /

# Restore specific database
docker exec -i necform-postgres psql -U necform necform < necform_backup.sql
```

## Monitoring & Logging

### View Logs
```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f postgres

# Last 100 lines
docker-compose logs --tail=100 postgres

# Since specific time
docker-compose logs --since 2024-01-01T00:00:00 postgres
```

### Container Metrics
```bash
# Real-time stats
docker stats

# Specific container
docker stats necform-postgres

# No stream (one-time)
docker stats --no-stream
```

## Security Considerations

### Environment Variables
- Never commit sensitive data to git
- Use Docker secrets for sensitive data
- Rotate passwords regularly
- Use strong passwords for production

### Network Security
- Use private networks for inter-service communication
- Restrict port exposure
- Implement proper firewall rules
- Use TLS for external communication

### Container Security
- Use official images when possible
- Keep images updated
- Scan images for vulnerabilities
- Run containers as non-root user
- Implement resource limits

## Best Practices
- Use health checks for all services
- Implement proper dependency management
- Use specific version tags for images
- Configure resource limits
- Implement logging strategy
- Use volume mounts for persistence
- Test containers locally before deployment
- Use .env files for configuration
- Implement proper cleanup strategies
- Monitor container resource usage

## Troubleshooting Common Issues

### Container Won't Start
```bash
# Check logs
docker-compose logs [service_name]

# Check if port is in use
netstat -ano | findstr :8080

# Check disk space
docker system df
```

### Database Connection Issues
```bash
# Check if PostgreSQL is running
docker-compose ps postgres

# Test database connection
docker exec -it necform-postgres psql -U necform -d necform

# Check network connectivity
docker network inspect necform_default
```

### Volume Permission Issues
```bash
# Fix volume permissions
docker exec -it necform-postgres chown -R postgres:postgres /var/lib/postgresql/data
```

## Important Notes
- Always stop containers before making changes to compose.yaml
- Use `docker-compose down -v` to completely reset (WARNING: deletes data)
- Backup volumes before major changes
- Monitor container resource usage
- Keep Docker and Docker Compose updated
- Use specific image versions in production
- Test backup and recovery procedures
- Document custom configurations
- Use environment-specific compose files