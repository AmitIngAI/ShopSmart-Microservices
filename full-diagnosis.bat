@echo off
echo ========================================
echo   COMPLETE SYSTEM DIAGNOSIS
echo ========================================
echo.

cd C:\Users\Amit\ShopSmart-Microservices

echo [1] DOCKER CONTAINERS STATUS:
docker-compose ps
echo.

echo [2] MONGODB CONNECTION:
docker exec mongodb mongosh --eval "db.adminCommand('ping')" 2>nul && echo [OK] MongoDB Connected || echo [FAIL] MongoDB Not Connected
echo.

echo [3] MYSQL CONNECTION:
docker exec mysql-db mysql -uroot -prootpassword -e "SELECT 1" 2>nul && echo [OK] MySQL Connected || echo [FAIL] MySQL Not Connected
echo.

echo [4] PRODUCTS IN MONGODB:
docker exec mongodb mongosh --eval "db.getSiblingDB('shopdb').products.countDocuments()"
echo.

echo [5] USERS IN MYSQL:
docker exec mysql-db mysql -uroot -prootpassword -e "SELECT COUNT(*) FROM userdb.users" 2>nul || echo [INFO] Users table empty or not created
echo.

echo [6] PRODUCT-SERVICE HEALTH:
curl -s http://localhost:8082/actuator/health 2>nul || echo [FAIL] Product-Service Not Responding
echo.

echo [7] API-GATEWAY HEALTH:
curl -s http://localhost:8080/actuator/health 2>nul || echo [FAIL] API-Gateway Not Responding
echo.

echo [8] PRODUCTS VIA API-GATEWAY:
curl -s http://localhost:8080/api/products
echo.

echo [9] PRODUCTS DIRECT FROM PRODUCT-SERVICE:
curl -s http://localhost:8082/api/products
echo.

echo [10] EUREKA REGISTERED SERVICES:
curl -s http://localhost:8761/eureka/apps | findstr "<app>"
echo.

echo ========================================
echo   DIAGNOSIS COMPLETE
echo ========================================
pause