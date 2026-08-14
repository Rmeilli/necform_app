# Necform AI Service

Service AI pour Necform avec intégration Google Gemini.

## Configuration

### 1. Obtenir une clé API Google Gemini

1. Allez sur [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Connectez-vous avec votre compte Google
3. Cliquez sur "Create API Key"
4. Copiez la clé API générée

### 2. Configurer le service

Dans `src/main/resources/application.properties`, remplacez :

```properties
gemini.api.key=YOUR_GEMINI_API_KEY_HERE
```

par votre clé API :

```properties
gemini.api.key=AIzaSyXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
```

## Démarrage

### Prérequis

- Java 17+
- Maven
- Eureka Server démarré sur le port 8761

### Démarrer le service

```bash
cd necform-ai-service
mvn spring-boot:run
```

Le service démarrera sur le port 8082.

## Endpoints

### POST /ai/chat

Chat avec l'assistant AI Necform.

**Request:**
```json
{
    "message": "Quelles formations proposez-vous ?"
}
```

**Response:**
```json
{
    "response": "Necform propose des formations dans plusieurs domaines..."
}
```

## Architecture

```
Frontend (4200)
    ↓
API Gateway (8080)
    ↓
AI Service (8082)
    ↓
Google Gemini API
```

## Prompt Engineering

Le service utilise un prompt système pour s'assurer que l'IA ne répond qu'aux questions liées à Necform et ne jamais inventer d'informations.

## Logging

Les logs incluent :
- Requêtes entrantes
- Temps de réponse Gemini
- Erreurs API

## Sécurité

- Ne jamais commiter la clé API dans le repository
- Utiliser des variables d'environnement en production
