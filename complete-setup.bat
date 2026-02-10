@echo off
echo ============================================
echo   ShopSmart - Complete Setup
echo ============================================
echo.

cd /d C:\Users\Amit\ShopSmart-Microservices

echo [1/7] Building common library...
cd shared\common-lib
call mvn clean install -DskipTests
if errorlevel 1 (
    echo [ERROR] Maven build failed!
    pause
    exit /b 1
)
cd ..\..
echo [OK] Common library built

echo.
echo [2/7] Building Docker images (10-15 min)...
docker-compose build
if errorlevel 1 (
    echo [ERROR] Docker build failed!
    pause
    exit /b 1
)
echo [OK] All images built

echo.
echo [3/7] Checking images...
docker images | findstr shopsmart

echo.
echo [4/7] Starting all services...
docker-compose up -d

echo.
echo [5/7] Waiting 2 minutes for services to start...
timeout /t 120

echo.
echo [6/7] Checking service status...
docker-compose ps

echo.
echo [7/7] Adding test products...
timeout /t 10
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d "{\"name\":\"Laptop\",\"price\":1299.99,\"category\":\"Electronics\",\"stock\":15,\"description\":\"Gaming Laptop\",\"imageUrl\":\"https://via.placeholder.com/300\"}"
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d "{\"name\":\"Mouse\",\"price\":29.99,\"category\":\"Accessories\",\"stock\":100,\"description\":\"Wireless Mouse\",\"imageUrl\":\"https://via.placeholder.com/300\"}"
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d "{\"name\":\"Keyboard\",\"price\":89.99,\"category\":\"Accessories\",\"stock\":50,\"description\":\"Mechanical Keyboard\",\"imageUrl\":\"https://via.placeholder.com/300\"}"

echo.
echo ============================================
echo   Setup Complete!
echo ============================================
echo.
echo   Frontend:    http://localhost:3000
echo   Eureka:      http://localhost:8761
echo   API Gateway: http://localhost:8080
echo   RabbitMQ:    http://localhost:15672 (guest/guest)
echo   Zipkin:      http://localhost:9411
echo.
echo Opening frontend...
start http://localhost:3000
echo.
pause