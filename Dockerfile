# Build Stage
FROM gradle:7.4.2-jdk11 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper files
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Ensure Gradle wrapper has execute permission
RUN chmod +x gradlew

# Copy the Gradle build files
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Download dependencies without running the full build
RUN ./gradlew build --no-daemon || return 0

# Copy the source code and other necessary files
COPY src /app/src
COPY run.sh /app/run.sh

# Make the run script executable
RUN chmod +x /app/run.sh

# Build the project
RUN ./gradlew clean build --no-daemon

# Runtime Stage
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/my-app.jar

# Copy the run.sh script from the build stage
COPY --from=build /app/run.sh /app/run.sh

# Ensure run.sh has execute permission
RUN chmod +x /app/run.sh

# Set the default command to execute the run script
CMD ["./run.sh"]
