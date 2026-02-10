@echo off
echo ============================================
echo  ShopSmart Microservices - Starting...
echo ============================================

echo [1/5] Building common library...
cd shared\common-lib
call mvn clean install -DskipTests
cd ..\..

echo [2/5] Checking Docker...
docker --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Docker is not running!
    echo Please start Docker Desktop and try again.
    pause
    exit /b 1
)

echo [3/5] Stopping existing containers...
docker-compose down

echo [4/5] Building and starting all services...
docker-compose up --build -d

echo [5/5] Waiting for services to be ready...
timeout /t 30 /nobreak

echo.
echo ============================================
echo  ShopSmart is now running!
echo ============================================
echo.
echo  Frontend:          http://localhost:3000
echo  API Gateway:       http://localhost:8080
echo  Eureka Dashboard:  http://localhost:8761
echo  RabbitMQ:          http://localhost:15672
echo  Zipkin:            http://localhost:9411
echo.
echo  To view logs: docker-compose logs -f
echo  To stop:      docker-compose down
echo ============================================

pause