FROM openjdk:8-jdk-alpine
COPY target/employee-rest-service-0.0.1-SNAPSHOT.jar employee-rest-service.jar
ENTRYPOINT ["java", "-jar", "employee-rest-service.jar"]
EXPOSE 8080
