@echo off
echo ============================================
echo   Creating All Dockerfiles
echo ============================================
echo.

cd C:\Users\Amit\ShopSmart-Microservices

echo [1/8] Creating Eureka Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM openjdk:17-jdk-slim
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8761
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > eureka-service\Dockerfile
echo    [OK] eureka-service\Dockerfile created

echo [2/8] Creating API Gateway Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM openjdk:17-jdk-slim
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8080
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > api-gateway\Dockerfile
echo    [OK] api-gateway\Dockerfile created

echo [3/8] Creating User Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM openjdk:17-jdk-slim
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8081
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > user-service\Dockerfile
echo    [OK] user-service\Dockerfile created

echo [4/8] Creating Product Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM openjdk:17-jdk-slim
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8082
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > product-service\Dockerfile
echo    [OK] product-service\Dockerfile created

echo [5/8] Creating Order Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM openjdk:17-jdk-slim
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8083
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > order-service\Dockerfile
echo    [OK] order-service\Dockerfile created

echo [6/8] Creating Payment Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM openjdk:17-jdk-slim
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8084
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > payment-service\Dockerfile
echo    [OK] payment-service\Dockerfile created

echo [7/8] Creating Frontend Dockerfile...
(
echo FROM node:18-alpine AS build
echo WORKDIR /app
echo COPY package*.json ./
echo RUN npm install
echo COPY . .
echo RUN npm run build
echo.
echo FROM nginx:alpine
echo COPY --from=build /app/build /usr/share/nginx/html
echo COPY nginx.conf /etc/nginx/conf.d/default.conf
echo EXPOSE 80
echo CMD ["nginx", "-g", "daemon off;"]
) > frontend\Dockerfile
echo    [OK] frontend\Dockerfile created

echo [8/8] Creating Frontend nginx.conf...
(
echo server {
echo     listen 80;
echo     server_name localhost;
echo.
echo     root /usr/share/nginx/html;
echo     index index.html;
echo.
echo     location / {
echo         try_files $uri $uri/ /index.html;
echo     }
echo.
echo     location /api/ {
echo         proxy_pass http://api-gateway:8080;
echo         proxy_set_header Host $host;
echo         proxy_set_header X-Real-IP $remote_addr;
echo     }
echo }
) > frontend\nginx.conf
echo    [OK] frontend\nginx.conf created

echo.
echo ============================================
echo   All Dockerfiles Created Successfully!
echo ============================================
echo.
pause