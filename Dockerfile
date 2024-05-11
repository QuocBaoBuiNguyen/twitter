# Use the official MinIO image
FROM minio/minio:latest

# Set environment variables for MinIO access and secret keys
# These should ideally be passed from outside using Docker secrets or environment variables at runtime
ENV MINIO_ROOT_USER=minioadmin
ENV MINIO_ROOT_PASSWORD=minioadmin

# Expose ports for MinIO server and console
EXPOSE 9000 9090

# Command to start MinIO server with the MinIO console
CMD ["minio", "server", "/data", "--console-address", ":9090", "--address", ":9000"]
