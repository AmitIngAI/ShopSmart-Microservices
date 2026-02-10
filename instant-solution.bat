@echo off
echo ============================================
echo   INSTANT SOLUTION - Direct JAR Method
echo ============================================
echo.

cd C:\Users\Amit\ShopSmart-Microservices

echo [1/3] Building JARs locally (no Docker)...

cd shared\common-lib
call mvn clean install -DskipTests
cd ..\..

echo Building services...
for %%s in (eureka-service api-gateway user-service product-service order-service payment-service) do (
    echo Building %%s...
    cd %%s
    call mvn clean package -DskipTests
    cd ..
)

echo.
echo [2/3] Creating simple Dockerfiles...

for %%s in (eureka-service api-gateway user-service product-service order-service payment-service) do (
    echo Creating Dockerfile for %%s...
    (
        echo FROM eclipse-temurin:17-jre-alpine
        echo COPY target/*.jar app.jar
        echo ENTRYPOINT ["java", "-jar", "app.jar"]
    ) > %%s\Dockerfile
)

echo.
echo [3/3] Starting services with pre-built JARs...

docker-compose down
docker-compose build
docker-compose up -d

echo.
echo ============================================
echo   DONE! Services starting...
echo ============================================
timeout /t 60

docker-compose ps

start http://localhost:3000
start http://localhost:8761

pause