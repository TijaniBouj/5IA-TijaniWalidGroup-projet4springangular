# Use OpenJDK 11 as the base image
FROM openjdk:11-jdk-slim

# Expose the application's port
EXPOSE 8082

# Copy the JAR file to the image
COPY target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/DevOps_Project-1.0.jar"]
