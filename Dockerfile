# syntax = docker/dockerfile:1.2
#
# Build stage
#
FROM maven:4.0.0-openjdk-18 AS build
WORKDIR /app
COPY . .
RUN mvn clean package assembly:single -DskipTests
#
# Package stage
#
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/2023-tpa-mama-18-1.0-SNAPSHOT.jar .
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar","2023-tpa-mama-18-1.0-SNAPSHOT.jar"]
