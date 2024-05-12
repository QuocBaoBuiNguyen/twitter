# Use the official LocalStack image as a parent image
FROM localstack/localstack:2.0

# Set the default region environment variable
ENV AWS_DEFAULT_REGION=eu-central-1

# Copy the initialization script into the container
COPY localstack-script.sh /docker-entrypoint-initaws.d/

# Make the script executable
RUN chmod +x /docker-entrypoint-initaws.d/localstack-script.sh

# Expose necessary ports
# 4566 for LocalStack main entrypoint
# 4510-4559 for additional LocalStack services
EXPOSE 4566 4510-4559

# Set the Docker Host environment variable
ENV DOCKER_HOST=unix:///var/run/docker.sock

# Mount the Docker socket
VOLUME ["/var/run/docker.sock"]

# Use LocalStack's entrypoint to run the initialization scripts and LocalStack
ENTRYPOINT ["localstack", "start"]
