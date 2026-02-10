@echo off
echo ============================================
echo   Fixing Dockerfiles - Updating Base Image
echo ============================================
echo.

cd C:\Users\Amit\ShopSmart-Microservices

echo [1/6] Fixing Eureka Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8761
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > eureka-service\Dockerfile
echo    [OK] Fixed

echo [2/6] Fixing API Gateway Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8080
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > api-gateway\Dockerfile
echo    [OK] Fixed

echo [3/6] Fixing User Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8081
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > user-service\Dockerfile
echo    [OK] Fixed

echo [4/6] Fixing Product Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8082
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > product-service\Dockerfile
echo    [OK] Fixed

echo [5/6] Fixing Order Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8083
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > order-service\Dockerfile
echo    [OK] Fixed

echo [6/6] Fixing Payment Service Dockerfile...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo RUN mvn dependency:go-offline -DskipTests
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8084
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > payment-service\Dockerfile
echo    [OK] Fixed

echo.
echo ============================================
echo   All Dockerfiles Fixed!
echo ============================================
echo.
echo Changed: openjdk:17-jdk-slim
echo      To: eclipse-temurin:17-jre-alpine
echo.
pause