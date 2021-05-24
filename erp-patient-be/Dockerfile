FROM openjdk:8-jdk-alpine

COPY target/erp-patient-be-docker.jar erp-patient-be-docker.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "erp-patient-be-docker.jar"]