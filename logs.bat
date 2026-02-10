@echo off
echo Select service to view logs:
echo 1. All services
echo 2. API Gateway
echo 3. User Service
echo 4. Product Service
echo 5. Order Service
echo 6. Payment Service
echo 7. Frontend
echo.
set /p choice="Enter choice (1-7): "

if "%choice%"=="1" docker-compose logs -f
if "%choice%"=="2" docker-compose logs -f api-gateway
if "%choice%"=="3" docker-compose logs -f user-service
if "%choice%"=="4" docker-compose logs -f product-service
if "%choice%"=="5" docker-compose logs -f order-service
if "%choice%"=="6" docker-compose logs -f payment-service
if "%choice%"=="7" docker-compose logs -f frontend