# Payment Processing & Transaction System

A production-style backend payment processing system built with Java and Spring Boot.

## Objective

Build a backend that demonstrates the complete payment lifecycle:

* Payment creation
* Payment processing
* Payment verification
* Payment status tracking
* Webhooks
* Failed-payment handling
* Refunds
* Transaction history
* Idempotency
* Database transactions
* Redis caching
* Kafka event-driven architecture
* Authentication and authorization
* Audit logging
* Payment analytics
* Fraud and risk analysis

## Technology Stack

* Java 21
* Spring Boot
* Spring Security
* MySQL
* Redis
* Apache Kafka
* Docker
* JUnit
* Mockito
* Maven
* Git

## Current Status

### Step 1 — Project Setup

* Spring Boot project created
* Maven configured
* Initial REST API created
* Git repository initialized

## Planned Payment Lifecycle

```text
CREATE
   ↓
PROCESSING
   ↓
SUCCESS
   ↓
REFUND
```

Failure path:

```text
CREATE
   ↓
PROCESSING
   ↓
FAILED
```

## API

Initial health endpoint:

```http
GET /api/v1/health
```

## Project Structure

```text
payment-processing-system/
└── payment-service/
    ├── src/
    ├── pom.xml
    └── ...
```

This project is an independent learning and portfolio project focused on backend engineering, transaction processing, distributed systems, and payment-system design.
