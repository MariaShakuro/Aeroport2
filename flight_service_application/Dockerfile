FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/flight_service_application-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL maintainer="msh479859@gmail.com"
LABEL name="flight_service_application"