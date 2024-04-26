FROM openjdk:21-slim
WORKDIR /app

ARG SERVICE_NAME
COPY /${SERVICE_NAME}/build/libs/*.jar /app/app.jar
EXPOSE 10100
ENTRYPOINT ["java", "-jar", "/app/app.jar"]