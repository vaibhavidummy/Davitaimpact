FROM openjdk:8-jdk-alpine

COPY target/central-cloud-config-docker.jar central-cloud-config-docker.jar

EXPOSE 8888
ENTRYPOINT ["java -jar central-cloud-config-docker.jar"]
