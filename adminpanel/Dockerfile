FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the rest of the application code to the container
COPY . /app

# Expose port 8000 for the application
EXPOSE 8080

RUN ls -lha
RUN microdnf install findutils

WORKDIR /app


CMD ["sh", "-c", "cd app && ./gradlew bootRun"]