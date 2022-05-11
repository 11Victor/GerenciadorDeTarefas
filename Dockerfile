FROM adoptopenjdk/openjdk8-openj9:alpine-slim

COPY target/tasks.jar /

ENTRYPOINT ["java", "-jar", "tasks.jar" ]
