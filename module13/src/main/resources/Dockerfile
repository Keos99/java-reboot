FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} /app
ENTRYPOINT ["java", "-jar",  "/app/module13-1.0-SNAPSHOT.jar"]