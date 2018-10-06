FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD mdh-driftstorning-admin-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongocontainer/driftstorning", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

