#!/bin/bash

echo "============================================"
echo " ShopSmart Microservices - Starting..."
echo "============================================"

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "ERROR: Docker is not running!"
    echo "Please start Docker and try again."
    exit 1
fi

echo "[1/5] Building common library..."
cd shared/common-lib
mvn clean install -DskipTests
cd ../..

echo "[2/5] Stopping existing containers..."
docker-compose down

echo "[3/5] Cleaning up old images (optional)..."
# docker system prune -f

echo "[4/5] Building and starting all services..."
docker-compose up --build -d

echo "[5/5] Waiting for services to be ready..."
sleep 30

echo ""
echo "============================================"
echo " ShopSmart is now running!"
echo "============================================"
echo ""
echo "  Frontend:          http://localhost:3000"
echo "  API Gateway:       http://localhost:8080"
echo "  Eureka Dashboard:  http://localhost:8761"
echo "  RabbitMQ:          http://localhost:15672"
echo "  Zipkin:            http://localhost:9411"
echo ""
echo "  To view logs: docker-compose logs -f [service-name]"
echo "  To stop:      docker-compose down"
echo "============================================"