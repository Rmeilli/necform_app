# Rapport d'avancement - Projet NECFORM

**Auteur** : [Votre nom]
**Date** : [Date du jour]
**Encadrant** : [Nom de l'encadrant]

---

## 1. Présentation du projet

NECFORM est une application web de gestion des formations destinée à faciliter l'organisation et le suivi des formations professionnelles.

L'application permet de :
- Gérer les utilisateurs (apprenants, formateurs, administrateurs)
- Gérer les entreprises clientes
- Gérer le catalogue de formations
- Planifier et gérer les sessions de formation
- Gérer les inscriptions aux sessions
- Traiter les demandes de nouvelles formations
- Gérer les documents associés
- Visualiser un tableau de bord de synthèse

---

## 2. Fonctionnalités développées

### 2.1 Gestion des utilisateurs
- Création, modification et suppression d'utilisateurs
- Gestion des rôles et des profils
- Association aux entreprises

### 2.2 Gestion des entreprises
- Création et gestion des entreprises clientes
- Association des utilisateurs aux entreprises

### 2.3 Gestion des formations
- Catalogue de formations avec description
- Création, modification et suppression de formations
- Gestion des types de formations

### 2.4 Gestion des sessions
- Planification des sessions de formation
- Gestion des dates et lieux
- Suivi des statuts (planifiée, en cours, terminée, annulée)

### 2.5 Gestion des inscriptions
- Inscription des utilisateurs aux sessions
- Suivi des statuts d'inscription (en attente, confirmée, annulée)

### 2.6 Demandes de formation
- Système de demande de nouvelles formations
- Workflow de validation des demandes
- Suivi des statuts (en attente, approuvée, refusée)

### 2.7 Gestion des documents
- Upload et gestion des documents
- Classification par type (contrat, facture, attestation, etc.)

### 2.8 Dashboard
- Vue synthétique de l'activité
- Statistiques globales

---

## 3. Modèle de données et relations entre entités

### 3.1 Vue d'ensemble des relations

Le système de gestion des formations repose sur les relations suivantes entre les entités principales :

```
Entreprise ──(1:N)──> DemandeFormation ──(N:1)──> Formation
                                              │
                                              │ (1:N)
                                              ↓
                                     SessionFormation ──(1:N)──> Inscription
                                              │                      │
                                              │ (N:1)                │ (N:1)
                                              ↓                      ↓
                                      Utilisateur (formateur)   Utilisateur (apprenant)

Document ──(N:1)──> SessionFormation
Document ──(N:1)──> Utilisateur
```

### 3.2 Explication des relations

**Entreprise ↔ DemandeFormation**
- Une entreprise peut faire plusieurs demandes de formation
- Chaque demande est liée à une entreprise cliente

**Formation ↔ SessionFormation**
- Une formation peut avoir plusieurs sessions (différentes dates/lieux)
- Chaque session est liée à une formation spécifique

**SessionFormation ↔ Utilisateur (formateur)**
- Une session est animée par un formateur (utilisateur de type FORMATEUR)
- Un formateur peut animer plusieurs sessions

**SessionFormation ↔ Inscription**
- Une session peut avoir plusieurs inscriptions
- Chaque inscription est liée à une session spécifique

**Inscription ↔ Utilisateur (apprenant)**
- Un apprenant (utilisateur de type APPRENANT) peut s'inscrire à plusieurs sessions
- Chaque inscription est liée à un apprenant

**DemandeFormation ↔ Formation**
- Une demande concerne une formation existante du catalogue
- Une formation peut faire l'objet de plusieurs demandes

**Document ↔ SessionFormation / Utilisateur**
- Un document peut être associé à une session (ex: attestation de présence)
- Un document peut être associé à un utilisateur (ex: contrat)

### 3.3 Flux métier principal

1. **Demande** : Une entreprise fait une demande pour une formation
2. **Planification** : Une session est créée pour cette formation avec un formateur
3. **Inscription** : Les apprenants s'inscrivent à la session
4. **Réalisation** : La session se déroule (statut passe de PLANIFIEE à EN_COURS puis TERMINEE)
5. **Documents** : Les documents sont générés (attestations, factures, etc.)

---

## 4. Sécurité et authentification

L'application utilise Keycloak pour la gestion de l'authentification et des autorisations :
- Connexion sécurisée des utilisateurs
- Gestion des rôles et des permissions
- Protection des données sensibles

**[EMPLACEMENT SCREENSHOT - Interface Keycloak]**
*(Insérer ici une capture de l'interface Keycloak montrant la gestion des utilisateurs/rôles)*

---

## 4. Documentation de l'API

Une interface Swagger a été mise en place pour documenter et tester l'API :
- Documentation interactive de tous les endpoints
- Possibilité de tester les requêtes directement
- Accès via : `http://localhost:8080/swagger-ui.html`

**[EMPLACEMENT SCREENSHOT - Interface Swagger]**
*(Insérer ici une capture de l'interface Swagger UI avec les endpoints)*

---

## 5. Tests

Les fonctionnalités ont été testées avec Postman :
- Collection de requêtes pour chaque module
- Vérification des réponses de l'API
- Tests des différents scénarios

**[EMPLACEMENT SCREENSHOT - Postman]**
*(Insérer ici une capture de Postman montrant un test réussi)*

---

## 6. État d'avancement

### ✅ Fonctionnalités terminées
- Gestion complète des utilisateurs
- Gestion des entreprises
- Gestion des formations et sessions
- Gestion des inscriptions
- Gestion des demandes de formation
- Gestion des documents
- Dashboard de synthèse
- Authentification et sécurité
- Documentation API (Swagger)

### 🔄 En cours
- Tests finaux avec Postman

### ⏳ À faire
- Frontend, Ai, etc...

---

## 7. Prochaines étapes

- Finalisation des tests
- Entamer la partie frontend avec Angular

---

## Annexes

### Accès à l'application

- **Interface Swagger** : http://localhost:8080/swagger-ui.html
- **Port de l'application** : 8080
