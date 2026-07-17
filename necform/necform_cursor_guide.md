# NECFORM - PROJECT RULES

## Project Overview

Build a MINI LMS platform for NECFORM.

Main objective:
Manage training courses, sessions, learners, documents and dashboards.

This is NOT a full LMS.

Priority:
Business features over technical complexity.

---

## Tech Stack

Backend:

* Java 21
* Spring Boot 3
* Spring Data JPA
* Spring Security
* Keycloak
* PostgreSQL
* Maven
* Lombok
* Spring Validation

Frontend:

* Angular

Infrastructure:

* Docker
* Docker Compose

API:

* REST

Documentation:

* Swagger OpenAPI

---

## Architecture

Use simple layered architecture.

com.necform

config/
controller/
service/
service/impl/
repository/
entity/
dto/
dto/request/
dto/response/
mapper/
exception/
security/
util/

---

## Authentication

Authentication MUST use Keycloak.

Rules:

* Never store passwords locally.
* Never implement custom login.
* Never create JWT manually.
* Roles come from Keycloak.

Roles:

ADMIN
FORMATEUR

Store only:

keycloakUserId
email

inside local entities.

---

## Entities (V1 only)

FORMATION
SESSION_FORMATION
APPRENANT
INSCRIPTION
DOCUMENT
FORMATEUR

Do NOT create advanced LMS entities.

Ignore:

* Payment
* Notification
* Attendance
* AI
* Multi-tenancy

---

## Entity Rules

All entities must:

* use UUID IDs
* use Lombok
* use JPA annotations
* use LocalDate / LocalDateTime
* use Enum where applicable

Example:

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

---

## DTO Rules

Never expose entities.

Always create:

CreateXRequest
UpdateXRequest
XResponse

Use Mapper classes.

---

## Service Rules

Service:
business only

Repository:
database only

Controller:
REST only

---

## API Rules

Convention:

GET /api/formations

GET /api/formations/{id}

POST /api/formations

PUT /api/formations/{id}

DELETE /api/formations/{id}

---

## Security Rules

Use:

@PreAuthorize

Examples:

ROLE_ADMIN

ROLE_FORMATEUR

No role logic in controller.

---

## Database

PostgreSQL

Naming:

snake_case

Tables:

formations
sessions
apprenants
documents
inscriptions
formateurs

---

## Development Order

STEP 1:
Formation

STEP 2:
Session

STEP 3:
Apprenant

STEP 4:
Inscription

STEP 5:
Document

STEP 6:
Dashboard

---

## Code Quality

Keep code simple.

Avoid overengineering.

Prefer readable code.

Generate only necessary files.

Always ask before introducing new frameworks.

---

## Testing

Generate tests only for services.

JUnit 5.

Avoid complex integration tests.

---

## Goal

Deliver a working MVP in 12 weeks.
