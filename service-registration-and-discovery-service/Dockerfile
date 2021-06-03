FROM openjdk:8-jdk-alpine

COPY target/service-registry-docker.jar service-registry-docker.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "service-registry-docker.jar"]
