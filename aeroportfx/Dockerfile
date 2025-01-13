FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/aeroportfx-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL maintainer="msh479859@gmail.com"
LABEL name="aeroportfx"