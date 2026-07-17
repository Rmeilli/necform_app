# Guide de Configuration Keycloak pour Necform

Ce guide explique comment configurer Keycloak pour l'authentification de l'application Necform.

## Prérequis

- Keycloak installé et démarré (généralement sur `http://localhost:8081`)
- Accès à la console d'administration Keycloak

## Étape 1 : Accéder à la console Keycloak

1. Ouvrez votre navigateur et allez sur : `http://localhost:8081`
2. Cliquez sur **Administration Console**
3. Connectez-vous avec vos identifiants administrateur (par défaut : `admin` / `admin`)

## Étape 2 : Créer le Realm

Un **realm** est un espace isolé dans Keycloak qui contient ses propres utilisateurs et rôles.

1. Dans le menu de gauche, cliquez sur le menu déroulant en haut (par défaut "Master")
2. Cliquez sur **Create realm**
3. Remplissez le formulaire :
   - **Name** : `necform`
   - **Enabled** : Coché
4. Cliquez sur **Create**

## Étape 3 : Créer le Client Frontend (Angular)

Un **client** représente une application qui utilise Keycloak pour l'authentification.

1. Dans le menu de gauche, cliquez sur **Clients**
2. Cliquez sur **Create client**
3. Remplissez le formulaire :
   - **Client type** : `OpenID Connect`
   - **Client ID** : `necform-frontend`
   - **Client authentication** : `Off` (pour une application publique SPA)
   - **Name** : `Necform Frontend`
   - **Description** : `Application Angular pour Necform`
4. Cliquez sur **Next**
5. **Capability config** :
   - **Client authentication** : `Off`
   - **Authentication flow** : Gardez les valeurs par défaut
6. Cliquez sur **Next**
7. **Login settings** :
   - **Valid redirect URIs** : `http://localhost:4200/*`
   - **Valid post logout redirect URIs** : `http://localhost:4200/*`
   - **Web origins** : `http://localhost:4200`
8. Cliquez sur **Save**

## Étape 4 : Créer le Client Backend (Spring Boot)

1. Dans le menu de gauche, cliquez sur **Clients**
2. Cliquez sur **Create client**
3. Remplissez le formulaire :
   - **Client type** : `OpenID Connect`
   - **Client ID** : `necform-backend`
   - **Client authentication** : `On` (pour une application backend sécurisée)
   - **Authentication flow** : Standard flow
   - **Name** : `Necform Backend`
   - **Description** : `API Spring Boot pour Necform`
4. Cliquez sur **Next**
5. **Capability config** :
   - **Client authentication** : `On`
   - **Authentication flow** : Gardez les valeurs par défaut
6. Cliquez sur **Next**
7. **Login settings** :
   - **Valid redirect URIs** : `http://localhost:8080/*`
   - **Valid post logout redirect URIs** : `http://localhost:8080/*`
   - **Web origins** : `http://localhost:8080`
8. Cliquez sur **Save**
9. **Important** : Après la création, notez le **Client Secret** dans l'onglet **Credentials**
   - Copiez ce secret, il sera nécessaire pour la configuration du backend

## Étape 5 : Créer les Rôles

Les rôles définissent les permissions des utilisateurs dans l'application.

1. Dans le menu de gauche, cliquez sur **Realm roles**
2. Cliquez sur **Create role**
3. Créez les rôles suivants :
   - **ADMIN** : Administrateur (accès complet, y compris gestion des utilisateurs)
   - **USER** : Utilisateur standard (accès limité aux fonctionnalités de base)
   - **FORMATEUR** : Formateur (accès aux formations et sessions)
4. Répétez l'opération pour chaque rôle

## Étape 6 : Créer les Utilisateurs

1. Dans le menu de gauche, cliquez sur **Users**
2. Cliquez sur **Add user**
3. Remplissez le formulaire :
   - **Username** : `admin`
   - **Email** : `admin@necform.ma`
   - **First name** : `Admin`
   - **Last name** : `User`
   - **Email verified** : Coché
   - **Enabled** : Coché
4. Cliquez sur **Create**
5. Cliquez sur l'onglet **Credentials**
6. Définissez un mot de passe :
   - **Password** : `admin123`
   - **Password confirmation** : `admin123`
   - **Temporary** : Décoché
7. Cliquez sur **Set password**
8. Cliquez sur l'onglet **Role mapping**
9. Cliquez sur **Assign role**
10. Sélectionnez le rôle **ADMIN** dans la liste des realm roles
11. Cliquez sur **Assign**

Répétez ces étapes pour créer d'autres utilisateurs avec différents rôles.

## Étape 7 : Configurer le Backend

Le backend est déjà configuré dans `application.properties` :

```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8081/realms/necform
```

Cette configuration indique à Spring Boot où trouver le serveur Keycloak et valider les tokens JWT.

## Étape 8 : Configurer le Frontend

Le frontend est déjà configuré dans `app.config.ts` :

```typescript
provideKeycloak({
  config: {
    url: 'http://localhost:8081',
    realm: 'necform',
    clientId: 'necform-frontend'
  },
  initOptions: {
    onLoad: 'login-required',
    flow: 'standard',
    checkLoginIframe: false
  }
})
```

## Étape 9 : Tester l'Authentification

1. Démarrez le backend Spring Boot :
   ```bash
   cd necform
   ./mvnw spring-boot:run
   ```

2. Démarrez le frontend Angular :
   ```bash
   cd necform-front
   npm start
   ```

3. Ouvrez votre navigateur sur `http://localhost:4200`

4. Vous devriez être automatiquement redirigé vers la page de connexion Keycloak

5. Connectez-vous avec l'utilisateur créé (ex: `admin` / `admin123`)

6. Après la connexion, vous serez redirigé vers l'application avec vos informations affichées dans la sidebar

## Étape 10 : Vérifier les Guards

1. Essayez d'accéder à la page `/utilisateurs` (réservée aux admins)
2. Si vous n'avez pas le rôle ADMIN, vous serez redirigé vers la page d'accueil
3. Si vous avez le rôle ADMIN, vous pourrez accéder à la page

## Dépannage

### Problème : "403 Forbidden" sur les requêtes API

**Cause** : Le backend ne valide pas correctement les tokens JWT.

**Solution** :
- Vérifiez que Keycloak est démarré
- Vérifiez que l'URL dans `application.properties` est correcte
- Vérifiez que le realm existe dans Keycloak

### Problème : "Unable to authenticate" dans le frontend

**Cause** : La configuration Keycloak dans le frontend est incorrecte.

**Solution** :
- Vérifiez que l'URL Keycloak est correcte dans `app.config.ts`
- Vérifiez que le client ID correspond à celui créé dans Keycloak
- Vérifiez que le realm existe dans Keycloak

### Problème : CORS errors

**Cause** : Le navigateur bloque les requêtes cross-origin.

**Solution** :
- Vérifiez la configuration CORS dans `SecurityConfig.java`
- Assurez-vous que `http://localhost:4200` est dans les origines autorisées

## Résumé de l'Architecture

```
┌─────────────────┐
│   Navigateur    │
│  (localhost:4200)│
└────────┬────────┘
         │
         │ 1. Redirection vers Keycloak
         ↓
┌─────────────────┐
│    Keycloak     │
│ (localhost:8081)│
│  - Authentifie  │
│  - Génère JWT   │
└────────┬────────┘
         │
         │ 2. Redirection avec token JWT
         ↓
┌─────────────────┐
│  Frontend       │
│  (Angular)      │
│  - Stocke token │
│  - Envoie token │
└────────┬────────┘
         │
         │ 3. Requête HTTP avec token
         ↓
┌─────────────────┐
│   Backend       │
│ (localhost:8080)│
│  - Valide JWT   │
│  - Extrait rôles│
│  - Autorise     │
└─────────────────┘
```

## Sécurité

- Les tokens JWT expirent après un certain temps (configurable dans Keycloak)
- Le frontend rafraîchit automatiquement les tokens avant leur expiration
- Le backend valide chaque token JWT à chaque requête
- Les rôles sont extraits du token et utilisés pour l'autorisation

## Prochaines Étapes

- Configurer le rafraîchissement automatique des tokens
- Ajouter des rôles plus spécifiques (ex: GESTION_FORMATIONS, GESTION_ENTREPRISES)
- Implémenter la gestion des permissions au niveau des méthodes avec `@PreAuthorize`
- Ajouter des tests d'intégration pour l'authentification
