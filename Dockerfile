# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# FROM gradle:7.4.2-jdk11 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper files
# COPY gradlew /app/gradlew
# COPY gradle /app/gradle

# Copy the source code to the container
COPY src /app/src

# Copy the build folder to the container
COPY build /app/build

# Copy the run script to the container
COPY run.sh /app/run.sh

# Make the run script executable
RUN chmod +x /app/run.sh

# Set the default shell to /bin/sh
SHELL ["/bin/sh", "-c"]

# Set the default command to execute the run script
CMD ["./run.sh"]
