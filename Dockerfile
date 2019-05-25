FROM openjdk:8-jdk-alpine

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/project-manager-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} project-manager-service.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/project-manager-service.jar"]

HEALTHCHECK --interval=5s \
            --timeout=5s \
            CMD curl -f http://127.0.0.1:8080 || exit 1