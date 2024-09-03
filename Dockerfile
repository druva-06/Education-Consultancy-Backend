FROM openjdk:17

ARG JAR_FILE=target/*jar

COPY ${JAR_FILE} education.jar

ENTRYPOINT ["java", "-jar", "/education.jar"]

EXPOSE 8080