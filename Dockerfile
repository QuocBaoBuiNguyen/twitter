# Use the official MinIO image as the base image
FROM minio/minio

# Set the working directory in the container
WORKDIR /data

# Copy the local file to the container's workspace
COPY . .

# Expose port 9000
EXPOSE 9000

# Command to run the executable
CMD ["minio", "server", "/data"]