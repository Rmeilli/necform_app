# 🐳 Docker - Necform

Ce document explique comment conteneuriser et exécuter l'application Necform avec Docker.

## 📁 Fichiers ajoutés

- `docker-compose.yml` : Orchestration de toute la stack
- `.env.example` : Variables d'environnement
- `necform/Dockerfile` : Backend Spring Boot
- `necform-front/Dockerfile` : Frontend Angular
- `necform-gateway/Dockerfile` : API Gateway
- `necform-ai-service/Dockerfile` : Service IA
- `necform-eureka-server/Dockerfile` : Service discovery

## 🚀 Démarrage rapide

### 1. Prérequis
- Docker Engine 24+
- Docker Compose v2+

### 2. Configuration
Copie le fichier d'exemple et remplis les valeurs si nécessaire :

```bash
cp .env.example .env
```

### 3. Démarrer la stack complète

```bash
docker compose up -d
```

### 4. Démarrer uniquement la base de données et le backend

```bash
docker compose up -d postgres eureka backend
```

### 5. Démarrer avec Keycloak (authentification)

```bash
docker compose --profile auth up -d
```

## 🌐 Ports

| Service | Port local | URL |
|---------|-----------|-----|
| Frontend | 4200 | http://localhost:4200 |
| Gateway | 8081 | http://localhost:8081 |
| Backend | 8080 | http://localhost:8080 |
| AI Service | 8082 | http://localhost:8082 |
| Eureka | 8761 | http://localhost:8761 |
| Keycloak | 8180 | http://localhost:8180 |
| PostgreSQL | 5432 | localhost:5432 |

## 🧹 Commandes utiles

```bash
# Arrêter tout
docker compose down

# Voir les logs
docker compose logs -f

# Reconstruire les images
docker compose up -d --build

# Supprimer les volumes (perdre les données)
docker compose down -v
```