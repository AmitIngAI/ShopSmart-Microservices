<<<<<<< HEAD
<<<<<<< HEAD
# 🛒 ShopSmart - Microservices E-Commerce Platform

[![CI/CD Pipeline](https://github.com/AmitIngAI/ShopSmart-Microservices/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/AmitIngAI/ShopSmart-Microservices/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18.2-blue.svg)](https://reactjs.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)

A production-ready **microservices-based e-commerce platform** built with Spring Boot, React, and Docker. This project demonstrates enterprise-level architecture patterns including service discovery, API gateway, event-driven communication, and distributed tracing.

## 🏗️ Architecture

┌─────────────────────────────────────────────────────────────────────────┐
│ FRONTEND │
│ (React + Tailwind CSS) │
│ localhost:3000 │
└─────────────────────────────────────────────────────────────────────────┘
│
▼
┌─────────────────────────────────────────────────────────────────────────┐
│ API GATEWAY │
│ (Spring Cloud Gateway) │
│ Circuit Breaker + Rate Limiting + Routing │
│ localhost:8080 │
└─────────────────────────────────────────────────────────────────────────┘
│
┌───────────────────────────┼───────────────────────────┐
│ │ │
▼ ▼ ▼
┌───────────────┐ ┌───────────────┐ ┌───────────────┐
│ User Service │ │Product Service│ │ Order Service │
│ (MySQL) │ │ (MongoDB) │ │ (MySQL) │
│ :8081 │ │ :8082 │ │ :8083 │
└───────────────┘ └───────────────┘ └───────────────┘
│ │
│ │
└───────────────────────┬───────────────────────────┘
│
▼
┌───────────────────────┐
│ Payment Service │
│ (Stripe + RabbitMQ) │
│ :8084 │
└───────────────────────┘


## 🚀 Features

- **Microservices Architecture** - 5 independent services with their own databases
- **Service Discovery** - Netflix Eureka for dynamic service registration
- **API Gateway** - Spring Cloud Gateway with routing and circuit breaker
- **Event-Driven** - RabbitMQ for asynchronous communication
- **Distributed Tracing** - Zipkin for request tracing across services
- **JWT Authentication** - Secure stateless authentication
- **Payment Integration** - Stripe payment gateway
- **Docker Support** - Full containerization with Docker Compose
- **Kubernetes Ready** - K8s manifests for production deployment
- **CI/CD Pipeline** - GitHub Actions for automated testing and deployment

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| Backend | Java 17, Spring Boot 3.2, Spring Cloud 2023 |
| Frontend | React 18, Tailwind CSS, Axios |
| Databases | MySQL 8, MongoDB 7 |
| Messaging | RabbitMQ |
| API Gateway | Spring Cloud Gateway |
| Service Discovery | Netflix Eureka |
| Tracing | Zipkin, Spring Cloud Sleuth |
| Security | Spring Security, JWT |
| Payments | Stripe API |
| Containerization | Docker, Docker Compose |
| Orchestration | Kubernetes |
| CI/CD | GitHub Actions |

## 📦 Quick Start

### Prerequisites

- Java 17+
- Maven 3.8+
- Node.js 18+
- Docker & Docker Compose
- Git

### Run with Docker (Recommended)

```bash
# Clone the repository
git clone https://github.com/AmitIngAI/ShopSmart-Microservices.git
cd ShopSmart-Microservices

# Build common library
cd shared/common-lib
mvn clean install -DskipTests
cd ../..

# Start all services
docker-compose up --build -d

# Check status
docker-compose ps
=======
# ShopSmart-Microservices
Full-stack microservices e-commerce platform with Spring Boot and React
>>>>>>> 81995c97316caaf96180f36617ab64ef0cac4497
=======
# 🛒 ShopSmart - Microservices E-Commerce Platform

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen?logo=springboot)
![React](https://img.shields.io/badge/React-18-blue?logo=react)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql)
![MongoDB](https://img.shields.io/badge/MongoDB-7-47A248?logo=mongodb)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-FF6600?logo=rabbitmq)
![License](https://img.shields.io/badge/License-MIT-yellow)

A production-ready microservices-based e-commerce application built with Spring Boot, React, and Docker.

## 💡 Problem Statement

Traditional monolithic e-commerce applications face scalability challenges. ShopSmart demonstrates modern microservices architecture to solve:
- Independent service scaling
- Technology flexibility per service
- Fault isolation and resilience


## 🛠️ Technology Stack

| Layer | Technology |
|-------|------------|
| **Backend Framework** | Spring Boot 3.2, Spring Cloud |
| **Frontend** | React 18, Tailwind CSS |
| **Databases** | MySQL 8 (Transactional), MongoDB 7 (Catalog) |
| **Messaging** | RabbitMQ |
| **Service Discovery** | Netflix Eureka |
| **API Gateway** | Spring Cloud Gateway |
| **Authentication** | JWT + Spring Security |
| **Observability** | Zipkin (Distributed Tracing) |
| **Resilience** | Resilience4j (Circuit Breaker) |
| **Containerization** | Docker, Docker Compose |
| **CI/CD** | GitHub Actions (Ready) |
| **Version Control** | Git, GitHub |

## 🏗️ System Architecture
 ┌─────────────┐
│ Browser │
│ (Client) │
└──────┬──────┘
│ HTTP
▼
┌─────────────────────────────────┐
│ API Gateway (8080) │
│ - Routing │
│ - Load Balancing │
│ - Circuit Breaker │
└────────┬────────────────────────┘
│
┌────┴──────┬─────────┬─────────┐
▼ ▼ ▼ ▼
┌────────┐ ┌─────────┐ ┌──────┐ ┌──────────┐
│ User │ │Product │ │Order │ │ Payment │
│Service │ │Service │ │Svc │ │ Service │
│(8081) │ │(8082) │ │(8083)│ │ (8084) │
│ │ │ │ │ │ │ │
│MySQL │ │MongoDB │ │MySQL │ │ Stripe │
└────────┘ └─────────┘ └──┬───┘ └────┬─────┘
│ │
┌────▼──────────▼────┐
│ RabbitMQ │
│ (Event Broker) │
└─────────────────────┘

     ┌──────────────────────┐
     │  Eureka Server       │
     │ (Service Registry)   │
     └──────────────────────┘


## ✨ Features

### Business Features
✅ User registration & JWT-based authentication  
✅ Product catalog with search & filtering  
✅ Shopping cart management  
✅ Order placement & tracking  
✅ Payment processing (Stripe integration)  
✅ Order history & status updates  

### Technical Features
✅ Microservices architecture (5 independent services)  
✅ API Gateway with dynamic routing  
✅ Service Discovery using Eureka  
✅ Event-driven communication (RabbitMQ)  
✅ Distributed tracing (Zipkin)  
✅ Circuit Breaker pattern (Resilience4j)  
✅ Polyglot persistence (MySQL + MongoDB)  
✅ Containerized deployment (Docker Compose)  
✅ Health monitoring (Spring Actuator)  

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Maven 3.8+
- Node.js 18+

### Run Locally

1. **Clone the repository**
git clone https://github.com/AmitIngAI/ShopSmart-Microservices.git
cd ShopSmart-Microservices

2..**Build common library**
cd shared/common-lib
mvn clean install
cd ../..

3..**Start all services**
docker-compose up -d

4..**Wait 2-3 minutes, then access:**
Frontend: http://localhost:3000
Eureka Dashboard: http://localhost:8761
API Gateway: http://localhost:8080
RabbitMQ: http://localhost:15672 (guest/guest)


👨‍💻 Author
Amit Ingale
📧 amitgingle@gmail.com
>>>>>>> d150e1c277c88205f8549ec2ad3d764b2cb02c4b
