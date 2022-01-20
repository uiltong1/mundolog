FROM openjdk:11

EXPOSE 8080

RUN apt-get update && \
 apt-get install -y netcat;

COPY /target/mundolog-api-0.0.1-SNAPSHOT.jar /app/mundolog.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/mundolog.jar"]


