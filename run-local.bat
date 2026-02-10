@echo off
echo ==========================================
echo  RUNNING WITHOUT FULL DOCKER
echo ==========================================
cd C:\Users\Amit\ShopSmart-Microservices

echo Starting only databases in Docker...
docker-compose up -d mysql-db mongodb rabbitmq

ping 127.0.0.1 -n 20 > nul

echo Starting services locally...
start cmd /k "cd eureka-service && mvn spring-boot:run"
ping 127.0.0.1 -n 30 > nul

start cmd /k "cd user-service && mvn spring-boot:run"
start cmd /k "cd product-service && mvn spring-boot:run"
start cmd /k "cd order-service && mvn spring-boot:run"
start cmd /k "cd payment-service && mvn spring-boot:run"
ping 127.0.0.1 -n 10 > nul

start cmd /k "cd api-gateway && mvn spring-boot:run"
ping 127.0.0.1 -n 20 > nul

echo Starting frontend...
cd frontend
if not exist node_modules npm install
start cmd /k "npm start"

echo ==========================================
echo Wait 2 minutes, then open http://localhost:3000
echo ==========================================
pause