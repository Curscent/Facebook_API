# Build stage
# Using a Maven image with Temurin JDK 21
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copy the POM file and dependencies first to leverage Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline
# Copy all project files
COPY . .
# Run package creation
RUN mvn clean package -DskipTests

# Package stage
# Using a Temurin JDK 21 Alpine image for a smaller runtime environment
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Use the correct artifact ID and version number for the JAR file
COPY --from=build /app/target/facebooklike-0.0.1-SNAPSHOT.jar app.jar
# FIX 1: EXPOSE the correct port (8001) as configured in application.properties
EXPOSE 8001
# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]