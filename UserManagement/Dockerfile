# Use OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven pom.xml file
COPY pom.xml .

# Download the dependencies (this helps with caching)
RUN mvn dependency:go-offline -B

# Copy the source code into the container
COPY src /app/src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Use a smaller image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory for the final image
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/User-1.0-SNAPSHOT.jar /app/UserManagementAPI.jar

# Expose the port the application will run on
EXPOSE 2525

# Run the application
ENTRYPOINT ["java", "-jar", "/app/UserManagementAPI.jar"]
