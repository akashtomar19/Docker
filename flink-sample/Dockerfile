# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set environment variables
ENV APP_HOME /app

# Create app directory
WORKDIR $APP_HOME

# Copy the built JAR file into the container
COPY target/flink-sample-1.0-SNAPSHOT.jar app.jar

# Expose port (optional, depending on your application)
EXPOSE 9999

# Define the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
