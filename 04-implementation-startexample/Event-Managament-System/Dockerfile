FROM amazoncorretto:21-alpine-full
VOLUME /main-app
COPY target/Event-Managament-System-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod,-dev","-jar","/app.jar"]
