# Use an OpenJDK base image
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build files to the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN ./mvnw package -DskipTests

# Expose the application port
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "target/middleware"]
