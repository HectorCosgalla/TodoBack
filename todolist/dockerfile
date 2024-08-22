FROM openjdk:21-jdk
ARG JAR_FILE=todolist/target/*.jar
COPY ${JAR_FILE} todolist/app.jar
ENTRYPOINT ["java","-jar","todolist/app.jar"]
