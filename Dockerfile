## EUREKA-SERVER

# Build stage with OpenJDK 11 and Gradle
FROM openjdk:11-jdk as builder

WORKDIR /app

# Copy Gradle executable and configuration files from ms-eureka-server
COPY ms-eureka-server/gradle /app/gradle
COPY ms-eureka-server/build.gradle /app/

# Copy common libraries
COPY lib /app/lib

# Copy source and resource files from ms-eureka-server
COPY ms-eureka-server/src /app/src

# Build the application
RUN ./gradlew clean build

# Run stage with OpenJDK 11 JRE slim
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]