FROM openjdk:8-jdk-alpine

COPY target/erp-admin-be-docker.jar erp-admin-be-docker.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "erp-admin-be-docker.jar"]
