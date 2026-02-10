@echo off
echo Fixing Maven dependency issue in Dockerfiles...
echo.

cd C:\Users\Amit\ShopSmart-Microservices

echo [1/6] Fixing Eureka Service...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8761
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > eureka-service\Dockerfile

echo [2/6] Fixing API Gateway...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8080
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > api-gateway\Dockerfile

echo [3/6] Fixing User Service...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8081
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > user-service\Dockerfile

echo [4/6] Fixing Product Service...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8082
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > product-service\Dockerfile

echo [5/6] Fixing Order Service...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8083
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > order-service\Dockerfile

echo [6/6] Fixing Payment Service...
(
echo FROM maven:3.8.4-openjdk-17 AS build
echo WORKDIR /app
echo COPY pom.xml .
echo COPY src ./src
echo RUN mvn clean package -DskipTests
echo.
echo FROM eclipse-temurin:17-jre-alpine
echo WORKDIR /app
echo COPY --from=build /app/target/*.jar app.jar
echo EXPOSE 8084
echo ENTRYPOINT ["java", "-jar", "app.jar"]
) > payment-service\Dockerfile

echo.
echo All Dockerfiles fixed!
echo.
echo Change: Removed "RUN mvn dependency:go-offline"
echo Now it will download dependencies during build directly.
echo.
pause