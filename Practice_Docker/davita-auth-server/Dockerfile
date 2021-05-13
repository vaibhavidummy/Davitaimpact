FROM openjdk:8-jdk-alpine

COPY target/DaVitaAuthServer.jar DaVitaAuthServer.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "DaVitaAuthServer.jar"]
