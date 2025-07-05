# Start with base image containing Java runtime
FROM openjdk:17-jdk-slim

# Information about image maintainer
LABEL maintainer="Waffle"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]