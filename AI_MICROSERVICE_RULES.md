# NECFORM AI MICROSERVICE - PROJECT INSTRUCTIONS

## Goal

Develop the Artificial Intelligence module as an independent Spring Boot microservice.

The existing LMS (Necform Backend) must NEVER communicate directly with Gemini.

All AI requests must go through a dedicated AI Microservice.

The architecture must remain clean, scalable and production-ready.

---

# Global Architecture

                   Angular

                       |

                Spring Cloud Gateway

                       |

        ----------------------------------

        |                                |

   Necform Backend                AI Microservice

        |                                |

   PostgreSQL                    Google Gemini API

        |                                |
        |
     Keycloak

        |
        |
   Eureka Server (Service Discovery)

The AI service owns all communication with Gemini.

The LMS Backend never calls Gemini directly.

---

# Microservices

There will be three Spring Boot applications.

## 1.

necform-eureka-server

Responsibilities:

- Service Discovery

- Service Registration

- Health Checks

Port: 8761

---

## 2.

necform

Responsibilities:

- formations

- sessions

- entreprises

- utilisateurs

- dashboard

- inscriptions

- demandes

- PostgreSQL

- Keycloak

No AI code here.

Port: 8081

---

## 3.

necform-ai-service

Responsibilities:

- AI

- Prompt Engineering

- Gemini API

- Chat

- Recommendation Engine

- Summaries

- Comparisons

No business entities.

No CRUD.

No database.

Only AI.

Port: 8082

---

# Spring Cloud Gateway

Create a dedicated Gateway project.

Project name

necform-gateway

Responsibilities

- API Gateway

- Routing

- CORS

- Authentication forwarding

- Future Rate Limiting

Routes

/api/**

↓

necform-backend

/ai/**

↓

necform-ai-service

The frontend communicates ONLY with the Gateway.

Never directly with backend or AI.

---

# Ports

Eureka Server

8761

Gateway

8080

Backend

8081

AI Service

8082

Keycloak

8083

PostgreSQL

5432

---

# Docker

Each service has its own Dockerfile.

A docker-compose.yml starts

Eureka Server

Gateway

Backend

AI

PostgreSQL

Keycloak

All services communicate using Docker networks.

---

# Communication

Backend

↓

REST

↓

AI Service

Never use direct Gemini calls from Backend.

---

# AI Endpoints

POST

/ai/chat

Request

{
    "message":"..."
}

Response

{
    "response":"..."
}

------------------------------------------------

POST

/ai/recommendation

Request

{
    "goal":"Learn DevOps"
}

------------------------------------------------

POST

/ai/summarize

Request

{
    "formationId":"..."
}

------------------------------------------------

POST

/ai/compare

Request

{
    "formationA":"Docker",
    "formationB":"Kubernetes"
}

------------------------------------------------

POST

/ai/prerequisites

Request

{
    "formation":"Spring Boot"
}

---

# AI Service Structure

src

 main

  java

   org.sid.necform.ai

        config

        controller

        service

        client

        dto

        prompt

        exception

---

# Services

GeminiClient

Responsible only for HTTP communication with Gemini.

Never contains business logic.

-------------------------------------

PromptBuilderService

Responsible for generating prompts.

-------------------------------------

RecommendationService

Builds recommendations.

-------------------------------------

SummaryService

Summarizes formations.

-------------------------------------

ChatService

General conversation.

---

# Prompt Engineering

Every prompt starts with

You are the official AI assistant of Necform.

Necform is a professional training organization.

You must ONLY answer questions related to Necform.

If the information is unavailable,
say you do not know.

Never invent formations.

Never invent prices.

Never invent durations.

Always answer in French.

---

# Future RAG

The architecture must allow future RAG implementation.

Today

Prompt

↓

Gemini

Tomorrow

User

↓

Gateway

↓

Backend

↓

Retrieve formations

↓

AI Service

↓

Gemini

The PromptBuilder must already be designed to accept contextual information.

---

# DTO

ChatRequest

ChatResponse

RecommendationRequest

RecommendationResponse

SummaryRequest

SummaryResponse

ComparisonRequest

ComparisonResponse

---

# Configuration

application.yml

contains

Gemini API Key

Gemini Model

Timeout

Temperature

Max Tokens

Never hardcode values.

---

# Logging

Use SLF4J.

Log

Incoming requests

Execution time

Gemini errors

Never log API keys.

---

# Exception Handling

Create a GlobalExceptionHandler.

Return proper JSON errors.

Never expose stack traces.

---

# Security

The AI service does not authenticate users.

Authentication is performed by Gateway.

Gateway forwards JWT.

Future versions may extract user roles.

---

# Code Quality

Use

Constructor Injection

Lombok

DTO

Service Layer

No duplicated code

Single Responsibility Principle

Clean Architecture

Readable methods

---

# Future Features

Conversation memory

Conversation history

RAG

PDF analysis

Document summarization

Training recommendation engine

Multilingual support

Voice support

---

# Development Roadmap

## Phase 1: Infrastructure

STEP 1

Create Eureka Server

STEP 2

Create API Gateway

STEP 3

Test service discovery

## Phase 2: AI Service

STEP 4

Create AI Microservice

STEP 5

Configure Spring Boot

STEP 6

Connect Gemini API

STEP 7

Implement /chat

STEP 8

Implement PromptBuilder

STEP 9

Connect Gateway

## Phase 3: Frontend Integration

STEP 10

Angular Chat Page

## Phase 4: Advanced AI Features

STEP 11

Recommendation Engine

STEP 12

Summaries

STEP 13

Comparisons

## Phase 5: Future Features

STEP 14

Future RAG

Always finish one step before starting the next.

Never implement multiple unfinished features simultaneously.