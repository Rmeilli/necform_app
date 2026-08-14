# AI MODULE MIGRATION - GEMINI TO HUGGING FACE

## Context

The current AI module uses the Google Gemini API.

This implementation must be completely migrated to Hugging Face Inference API.

The goal is to remove every Gemini dependency and replace it with Hugging Face while preserving a clean microservice architecture.

---

# Global Architecture

Angular

↓

Spring Cloud Gateway

↓

AI Microservice

↓

Hugging Face Inference API

↓

Open-source LLM
(Mistral / Llama / Qwen)

The Backend must NEVER communicate directly with Hugging Face.

Only the AI Microservice is allowed to communicate with external AI providers.

---

# Objectives

- Remove every Gemini reference.
- Remove every Gemini DTO.
- Remove Gemini configuration.
- Remove Gemini HTTP client.
- Keep the existing clean architecture.
- Replace Gemini with Hugging Face Chat Completion API.

---

# New AI Architecture

src/main/java/org/sid/necform/ai

    config/

        HuggingFaceConfig.java

    controller/

        ChatController.java

    service/

        ChatService.java

        PromptBuilderService.java

    client/

        HuggingFaceClient.java

    dto/

        ChatRequest.java

        ChatResponse.java

        HuggingFaceRequest.java

        HuggingFaceResponse.java

    exception/

        GlobalExceptionHandler.java

---

# Configuration

application.yml

Create a dedicated configuration section.

Example

ai:

  provider: huggingface

  huggingface:

    api-key: ${HF_API_KEY}

    model: mistralai/Mistral-7B-Instruct-v0.3

    base-url: https://router.huggingface.co/v1

    timeout: 30s

Do not hardcode values.

---

# Authentication

Every HTTP request must include

Authorization

Bearer ${HF_API_KEY}

Content-Type

application/json

---

# HTTP Client

Use Spring WebClient.

Create a dedicated client.

HuggingFaceClient

Responsibilities

- Build HTTP request
- Send request
- Parse response
- Handle HTTP errors

No business logic.

---

# Chat Endpoint

POST

/ai/chat

Request

{
    "message":"Je voudrais apprendre Docker."
}

Response

{
    "response":"..."
}

---

# Prompt Builder

Create PromptBuilderService.

Never send the raw user message.

Always prepend the system prompt.

Example

You are Necform AI Assistant.

Necform is a professional training center.

Only answer questions related to professional training.

Never invent prices.

Never invent durations.

Never invent formations.

Always answer in French.

Then append the user's question.

---

# Future Context Injection

PromptBuilderService must already support contextual information.

Method

buildPrompt(

String question,

String context

)

The context parameter will later contain data coming from PostgreSQL.

Do not implement RAG yet.

Just prepare the architecture.

---

# Chat Service

Responsibilities

Receive the user message

↓

Build prompt

↓

Call HuggingFaceClient

↓

Return response

No HTTP code.

No JSON parsing.

No controller logic.

---

# Controller

Only expose REST endpoints.

POST

/ai/chat

No business logic.

---

# DTO

ChatRequest

message

ChatResponse

response

HuggingFaceRequest

messages

model

temperature

max_tokens

HuggingFaceResponse

Map the official Hugging Face Chat Completion response.

---

# Error Handling

Implement GlobalExceptionHandler.

Return JSON errors.

Example

{
    "status":500,
    "message":"Unable to contact AI provider."
}

Never expose stack traces.

---

# Logging

Log

Incoming requests

Execution time

Provider response time

HTTP errors

Never log API keys.

---

# Security

Do not expose Hugging Face API key.

Never return API keys.

Never store API keys in Git.

Read from application.yml or environment variables.

---

# Dependencies

Required

Spring Boot Web

Spring Boot WebFlux

Lombok

Validation

Optional

Spring Cloud Gateway

---

# Remove

Delete every Gemini class.

Examples

GeminiClient

GeminiConfig

GeminiRequest

GeminiResponse

GeminiService

GeminiProperties

Every Gemini package.

Every Gemini import.

Every Gemini property.

---

# Quality Rules

Use constructor injection.

Use Lombok.

Follow SOLID principles.

Follow clean architecture.

Keep classes small.

No duplicated code.

No static utility classes.

Readable method names.

---

# Future Features

The architecture must easily support

- RAG

- Conversation history

- Recommendation engine

- Formation summarization

- PDF analysis

- Multiple AI providers

The provider should be replaceable without changing controllers or services.

Future providers

OpenAI

Mistral AI

Gemini

Ollama

Azure OpenAI

Only the client layer should change.

---

# Deliverables

The migration is complete when

✓ All Gemini code has been removed.

✓ The project compiles.

✓ POST /ai/chat works.

✓ AI responses come from Hugging Face.

✓ Configuration is externalized.

✓ Architecture remains clean.

✓ No hardcoded secrets.

✓ Ready for future RAG implementation.

---

IMPORTANT

Do NOT rewrite the whole project.

Modify ONLY the AI module.

Preserve package naming conventions.

Preserve coding style.

Preserve the existing REST API.

Do not introduce unnecessary complexity.

The objective is a clean migration from Gemini to Hugging Face while keeping the project maintainable.