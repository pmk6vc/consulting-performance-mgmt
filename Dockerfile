# Build JAR
FROM maven:3.8.7-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
# TODO: Figure out if it's possible to run tests here (or in a different part of CICD pipeline)
RUN mvn -f pom.xml clean package -Dmaven.test.skip

# Use built JAR to start service
FROM amazoncorretto:17-alpine
COPY --from=build /app/target/consulting-performance-mgmt-0.0.1-SNAPSHOT.jar /app/target/consulting-performance-mgmt-0.0.1-SNAPSHOT.jar
# TODO: Figure out how to link this to the exposed port in application.properties
EXPOSE 8000
ENTRYPOINT ["java","-jar","/app/target/consulting-performance-mgmt-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["sleep", "infinity"]
