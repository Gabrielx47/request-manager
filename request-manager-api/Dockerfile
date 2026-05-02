FROM openjdk:21-ea-1-jdk
LABEL authors="gabriel"

WORKDIR /request-manager-api

COPY target/request-manager-api-0.0.1-SNAPSHOT.jar /request-manager-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/request-manager-api-0.0.1-SNAPSHOT.jar"]