FROM   openjdk:17

EXPOSE 8080

ENV TZ="Africa/Nairobi"

COPY target/taskmanager-0.0.1-SNAPSHOT.jar taskmanager.jar

ENTRYPOINT ["java", "-jar","taskmanager.jar"]