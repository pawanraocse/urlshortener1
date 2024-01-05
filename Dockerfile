#Use an official OpenJDK runtime as a base image with Java17
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/url_shortener.jar /app/url_shortener.jar

# Set the default command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "url_shortener.jar"]
