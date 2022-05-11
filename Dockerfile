FROM openjdk:8-jdk-alpine
COPY target/tasks.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
