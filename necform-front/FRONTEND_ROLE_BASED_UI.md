# FRONTEND ROLE-BASED UI SPECIFICATION

## Project Context

Necform is a Learning Management System (LMS) built with:

- Angular 20
- Spring Boot
- PostgreSQL
- Keycloak

Authentication is already implemented using Keycloak.

The application currently has a single interface shared by every user.

The objective is to transform the frontend into a professional role-based application.

There must be only ONE Angular application.

The displayed interface depends on the authenticated user's Keycloak role.

---

# Existing Roles

The application already contains these Keycloak roles:

- ADMIN
- FORMATEUR
- ENTREPRISE
- APPRENANT

The role is available inside the JWT token.

Do NOT hardcode the current role.

Always retrieve it from Keycloak.

---

# Objectives

The frontend must dynamically adapt according to the authenticated user's role.

This includes:

- Sidebar
- Dashboard
- Navigation
- Accessible pages
- Visible actions
- CRUD permissions

The goal is to make the application look like a real professional LMS.

---

# Authentication

Reuse the existing Keycloak authentication.

Improve the existing AuthService if necessary.

The AuthService should expose:

- currentUser()
- currentRole()
- logout()

Create a dedicated RoleService.

Methods:

- isAdmin()
- isTrainer()
- isCompany()
- isLearner()
- hasRole(role)

---

# Route Guards

Create dedicated Angular Route Guards.

- AdminGuard
- TrainerGuard
- CompanyGuard
- LearnerGuard

If the user tries to access an unauthorized page:

Redirect to:

/access-denied

---

# Sidebar

The sidebar must be generated dynamically.

Do NOT create four sidebars.

Create a menu configuration based on roles.

Example:

interface MenuItem {

label: string

icon: string

route: string

roles: string[]

}

Generate the menu dynamically according to the connected user.

---

# ADMIN Interface

The administrator has full access.

Sidebar:

Dashboard

Demandes

Entreprises

Formations

Sessions

Inscriptions

Utilisateurs

Assistant IA

Paramètres

Permissions:

Full CRUD

Dashboard:

Statistics cards

Charts

Recent requests

Upcoming sessions

Latest users

---

# FORMATEUR Interface

Sidebar:

Dashboard

Mes Sessions

Mes Apprenants

Calendrier

Assistant IA

Mon Profil

Permissions:

View only assigned sessions

View learners

Update session status

Cannot:

Delete formations

Manage users

Manage companies

---

# ENTREPRISE Interface

Sidebar:

Dashboard

Catalogue

Mes Demandes

Mes Formations

Mes Employés

Assistant IA

Mon Profil

Permissions:

Browse catalogue

Create training requests

Track requests

View employees

No CRUD on sessions.

---

# APPRENANT Interface

Sidebar:

Accueil

Mes Formations

Calendrier

Mes Documents

Assistant IA

Mon Profil

Permissions:

Read only.

No CRUD.

Cannot modify data.

---

# Dashboards

Every role has its own dashboard.

ADMIN

Display:

Total formations

Total sessions

Total entreprises

Total apprenants

Upcoming sessions

Recent requests

Charts

------------------------------------------------

FORMATEUR

Display:

My sessions

My learners

Calendar

Sessions this week

------------------------------------------------

ENTREPRISE

Display:

My requests

My trainings

Employees registered

------------------------------------------------

APPRENANT

Display:

Next training

Calendar

My documents

Training history

---

# Existing Modules

Reuse all existing modules.

Do NOT recreate CRUD pages.

Existing pages:

Dashboard

Demandes

Entreprises

Formations

Sessions

Inscriptions

Utilisateurs

Only change:

Visibility

Permissions

Navigation

---

# Calendar

The Sessions page already contains FullCalendar.

Reuse it.

Admin:

View all sessions.

Trainer:

View only assigned sessions.

Learner:

View enrolled sessions.

Company:

View company training sessions.

---

# Header

Display:

Avatar

Full name

Email

Current role

Logout

---

# Access Denied

Create a modern page:

/access-denied

Display:

403

Access denied

Button:

Return to Dashboard

---

# Redirections

After login:

ADMIN

/dashboard

FORMATEUR

/trainer/dashboard

ENTREPRISE

/company/dashboard

APPRENANT

/learner/dashboard

---

# UI Requirements

Modern

Professional

Minimal

Responsive

Rounded cards

Soft shadows

Reuse existing colors

Desktop

Tablet

Mobile

---

# Backend

Do NOT modify backend APIs.

Assume Spring Security already protects endpoints.

Frontend only controls:

Visibility

Navigation

User experience

---

# Code Quality

Angular Standalone Components

Angular Signals when appropriate

No duplicated code

SOLID principles

Reusable components

Readable code

Strict typing

---

# Deliverables

The implementation is complete when:

- Roles are read from Keycloak JWT.
- Sidebar changes dynamically.
- Dashboard changes dynamically.
- Route guards work.
- Unauthorized pages are blocked.
- Existing CRUD pages are reused.
- No duplicated code.
- The application looks like a real LMS.

---

# IMPORTANT

Do NOT rewrite the whole frontend.

Reuse the existing components.

Keep the current design.

Only improve the application by introducing role-based interfaces.

Prioritize maintainability, scalability and clean architecture.

IMPORTANT:

Before writing any code, analyze the current Angular project.

Reuse the existing pages, services, layouts and components whenever possible.

Do NOT rewrite working code.

Implement the feature incrementally:

Phase 1:
- Role detection
- AuthService improvements
- RoleService
- Route Guards

Phase 2:
- Dynamic Sidebar
- Dynamic Header
- Dynamic Dashboard

Phase 3:
- Restrict pages and actions according to the role
- Hide unauthorized buttons
- Final UI polish

Always prefer modifying existing components instead of creating duplicates.