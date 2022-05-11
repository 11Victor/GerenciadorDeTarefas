FROM adoptopenjdk/openjdk8-openj9:alpine-slim

COPY GerenciadorDeTarefas/target/tasks.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar" ]
