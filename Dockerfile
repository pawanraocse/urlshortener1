#Use an official OpenJDK runtime as a base image with Java17
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/url_shortener.jar /app/jars/
# Copy jar to a separate directory

CMD ["java", "-jar", "/app/jars/url_shortener.jar"]
# Update CMD accordingly
