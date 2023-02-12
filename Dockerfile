# Build JAR
FROM maven:3.8.7-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn -f pom.xml clean package -Dmaven.test.skip

# Use built JAR to start service
FROM amazoncorretto:17-alpine
COPY --from=build /app/target/consulting-performance-mgmt-0.0.1-SNAPSHOT.jar /app/target/consulting-performance-mgmt-0.0.1-SNAPSHOT.jar
# Set Spring server port based on env variable (useful to connect docker-compose and Dockerfile with single env config)
ARG SERVER_PORT
ENV SERVER_PORT=$SERVER_PORT
EXPOSE $SERVER_PORT
ENTRYPOINT ["java","-jar","/app/target/consulting-performance-mgmt-0.0.1-SNAPSHOT.jar"]
