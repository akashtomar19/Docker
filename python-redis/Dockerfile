# Use an official Python runtime as the base image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the current directory contents into the container
COPY . .

# Install the required Python libraries
RUN pip install --no-cache-dir redis

# Run the Python script when the container starts
CMD ["python", "./set_redis_key.py"]
