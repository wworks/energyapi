# Use the official Python image from the Docker Hub
FROM python:3.10-slim

# Set the working directory in the container
WORKDIR /app

# Copy the requirements file into the container at /app
COPY requirements.txt /app/

# Install any dependencies specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application code to the container
COPY . /app

# Expose port 80 for the application
EXPOSE 80

# Specify the command to run the Streamlit app
CMD ["streamlit", "run", "main.py", "--server.port=80", "--server.address=0.0.0.0"]
