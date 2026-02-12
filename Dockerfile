# Use a base image with Java 21
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the JAR file built by Maven
COPY target/observability-lab-1.0.0.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
