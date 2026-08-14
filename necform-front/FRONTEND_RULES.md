# NECFORM FRONTEND - AI INSTRUCTIONS

## Project

Necform est une plateforme de gestion d'un organisme de formation.

Le backend est développé en :

- Spring Boot 4
- Java 22
- PostgreSQL
- Keycloak
- API REST

Le frontend doit être développé en Angular (version récente).

Le backend existe déjà.

L'objectif est uniquement de consommer les API existantes.

Ne jamais modifier le backend.

---

# Technologies

Toujours utiliser :

- Angular Standalone Components
- Angular Router
- HttpClient
- Bootstrap 5
- Bootstrap Icons
- RxJS
- TypeScript strict

Ne jamais utiliser :

- Angular Material
- PrimeNG
- jQuery
- Tailwind
- NgModules

---

# Architecture

Toujours respecter cette architecture.

src/

    app/

        core/

            interceptors/

            guards/

            services/

            models/

        shared/

            components/

            layout/

            pipes/

        features/

            dashboard/

            formations/

            sessions/

            utilisateurs/

            entreprises/

            inscriptions/

            demandes/

        app.routes.ts

        app.config.ts

---

# Backend

Toutes les requêtes utilisent

http://localhost:8080/api

Ne jamais inventer de nouvelles routes.

Toujours utiliser les endpoints Spring Boot existants.

---

# Fonctionnement

Chaque module possède :

- une page

- un service

- éventuellement des composants réutilisables

Exemple :

formations/

    formation.component

    formation.service

    formation.model

---

# Dashboard

Le dashboard affiche uniquement :

- Nombre de formations

- Nombre de sessions

- Nombre d'utilisateurs

- Nombre d'entreprises

- Nombre d'inscriptions

- Nombre de demandes

Ces données proviennent de

GET /api/dashboard

Ne jamais recalculer ces statistiques côté Angular.

---

# CRUD

Chaque écran CRUD possède :

- tableau Bootstrap

- bouton Ajouter

- bouton Modifier

- bouton Supprimer

- confirmation avant suppression

- formulaire Bootstrap

Pas de popup complexe.

Pas de bibliothèque externe.

---

# Design

Style moderne.

Utiliser Bootstrap uniquement.

Layout :

Sidebar fixe à gauche

Navbar en haut

Contenu à droite

Responsive.

Couleurs :

Primary :

#0d6efd

Fond :

#f8f9fa

Cartes :

blanches

Coins arrondis

Ombres légères.

---

# Sidebar

Toujours afficher :

Dashboard

Formations

Sessions

Utilisateurs

Entreprises

Demandes

Inscriptions

Déconnexion

---

# Navbar

Afficher :

Nom de l'utilisateur connecté

Rôle

Photo par défaut

---

# Login

Le login sera réalisé avec Keycloak.

Ne jamais créer une page de login personnalisée.

Toujours prévoir une intégration Keycloak.

---

# Sécurité

Utiliser :

HTTP Interceptor

pour ajouter automatiquement

Authorization

Bearer Token

sur toutes les requêtes.

Ne jamais stocker le mot de passe.

---

# Code

Toujours :

code propre

petites méthodes

services séparés

interfaces TypeScript

strict mode

pas de duplication

---

# API

Toutes les réponses utilisent les DTO du backend.

Ne jamais créer de nouveaux champs.

Ne jamais renommer un champ provenant du backend.

---

# Qualité

Avant de générer du code :

- vérifier si un composant existe déjà

- réutiliser les composants

- respecter l'architecture

- ne jamais casser le code existant

---

# Priorités

Développer dans cet ordre :

1 Dashboard

2 Layout

3 Formations

4 Sessions

5 Utilisateurs

6 Entreprises

7 Demandes

8 Inscriptions

9 Keycloak

10 Responsive

11 Optimisations

Ne jamais développer plusieurs modules en même temps.

Toujours terminer un module avant de passer au suivant.

---

# Important

Le backend est considéré comme la source de vérité.

Angular ne doit jamais modifier la logique métier.

Toute la logique métier reste dans Spring Boot.

Angular est uniquement responsable :

- affichage

- navigation

- appels API

- validation simple des formulaires
