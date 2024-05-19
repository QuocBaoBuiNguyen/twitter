# Build stage with OpenJDK 11 and Gradle
FROM gradle:8.1.0-jdk17-alpine as build


WORKDIR /app


# Copy Gradle executable and configuration files from ms-email-service
COPY ms-email-service/gradle /app/gradle
COPY ms-email-service/build.gradle /app/


# Copy common libraries
COPY lib /app/lib
COPY settings.gradle /app/


# Copy source and resource files from ms-email-service
COPY ms-email-service/src /app/src


# Build the application
RUN gradle clean build --no-daemon


# Run stage with OpenJDK 11 JRE slim
FROM openjdk:17.0.1-jdk-slim


WORKDIR /app


# Copy the built JAR file from the builder stage
COPY --from=build /app/build/libs/*.jar app.jar


# Command to run the application
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]