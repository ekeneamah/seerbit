# Use the base image with JDK and Maven installed
FROM adoptopenjdk:11-jdk-hotspot as builder

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a smaller base image for the final image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/middleware.jar .

# Set the command to run the application
CMD ["java", "-jar", "middleware.jar"]
