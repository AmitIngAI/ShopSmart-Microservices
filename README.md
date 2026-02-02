# 🛒 ShopSmart - Microservices E-Commerce Platform

## 📌 Project Overview
A full-stack microservices-based e-commerce platform built with Spring Boot, React, and Docker.

## 🏗️ Architecture
- **Backend:** Java 17 + Spring Boot Microservices
- **Frontend:** React.js + Tailwind CSS
- **Databases:** MySQL + MongoDB
- **Message Queue:** RabbitMQ
- **Service Discovery:** Eureka
- **API Gateway:** Spring Cloud Gateway
- **Containerization:** Docker + Docker Compose

## 📂 Project Structure
ShopSmart-Microservices/
├── backend/
│ ├── eureka-server/
│ ├── api-gateway/
│ ├── user-service/
│ ├── product-service/
│ ├── order-service/
│ └── payment-service/
├── frontend/
│ └── shopsmart-ui/
├── docker/
│ ├── docker-compose.yml
│ └── Dockerfile
├── postman-collections/
└── docs/

text


## 🚀 Services

| Service | Port | Database | Description |
|---------|------|----------|-------------|
| Eureka Server | 8761 | - | Service Discovery |
| API Gateway | 8080 | - | Single Entry Point |
| User Service | 8081 | MySQL | User Management & Auth |
| Product Service | 8082 | MongoDB | Product Catalog |
| Order Service | 8083 | MySQL | Order Management |
| Payment Service | 8084 | - | Payment Processing |

## 📦 Prerequisites
- Java 17+
- Maven 3.8+
- Node.js 18+
- MySQL 8+
- MongoDB 6+
- Docker & Docker Compose

## 🛠️ Setup Instructions
Coming soon...

## 👨‍💻 Author
Your Name

## 📄 License
MIT