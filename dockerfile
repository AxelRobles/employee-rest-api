FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY /startApp.sh /
ENTRYPOINT "/startApp.sh"