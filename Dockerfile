# Stage 1: build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Cachear dependencias primero (si el pom no cambia, esta capa se reutiliza)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: runtime (imagen mucho más liviana)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Usuario no-root por seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]