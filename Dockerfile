FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/tasks.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]