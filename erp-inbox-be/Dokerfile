FROM openjdk:8-jdk-alpine

COPY target/erp-inbox-be-docker.jar erp-inbox-be-docker.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "erp-inbox-be-docker.jar"]
