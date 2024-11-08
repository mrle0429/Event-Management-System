FROM amazoncorretto:21-alpine-jdk
VOLUME /main-app
COPY target/EventManagementSystem-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
