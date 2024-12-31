FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
COPY /target/career-advice-point-backend-0.0.1.jar /app

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "career-advice-point-backend-0.0.1.jar"]