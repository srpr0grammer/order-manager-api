FROM openjdk

# Set the working directory in the container
WORKDIR /app

# Copy the application jar file to the container
COPY target/order-manager-api.jar /app/order-manager-api.jar

# Set the command to run the jar file
CMD ["java", "-jar", "order-manager-api.jar"]