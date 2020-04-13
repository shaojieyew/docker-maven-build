FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=/target/dockerapp.jar

COPY ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
