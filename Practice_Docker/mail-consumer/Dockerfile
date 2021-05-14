FROM openjdk:8-jdk-alpine

COPY target/erp-mail-consumer-docker.jar erp-mail-consumer-docker.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "erp-mail-consumer-docker.jar"]
