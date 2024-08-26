# Vulnerable Application for Training Purposes

This project contains intentionally vulnerable applications across various technology stacks. It is designed for security training and testing purposes. **All secrets and passwords are safe to be leaked** as part of this educational exercise.

## Project Overview

The application is divided into three main components:

1. **Backend:** Python FastAPI with MongoDB
2. **Frontend:** Streamlit
3. **Admin Panel:** Java Spring

### 1. Backend (FastAPI + MongoDB)
The backend is built using FastAPI, a modern, fast (high-performance) web framework for building APIs with Python. It connects to a MongoDB database for data storage.

### 2. Frontend (Streamlit)
The frontend is built using Streamlit, a simple and powerful tool to create web apps for machine learning and data science projects.

### 3. Admin Panel (Java Spring)
The admin panel is developed using Java Spring, providing administrative control over the application.

## Deployment and Setup

The entire application can be deployed using Docker Compose. Ensure that you have Docker and Docker Compose installed on your machine.

### Prerequisites

- Docker and Docker Compose installed
- Port **8080** should be free on your local machine

### Steps to Deploy

1. Clone the repository:
    ```bash
    git clone <repository_url>
    cd <repository_directory>
    ```

2. Run Docker Compose:
    ```bash
    docker compose up
    ```

3. Docker Compose will automatically build and start all services.

4. Access the application at the following URLs:
    - **Backend (FastAPI):** `http://energy-api.docker.localhost`
    - **Frontend (Streamlit):** `http://dashboard.docker.localhost`
    - **Admin Panel (Java Spring):** `http://adminpanel.docker.localhost`

### Stopping the Application

To stop the application, run:
```bash
docker compose down
```

## Purpose

This project is intentionally vulnerable and should only be used in controlled environments for security training and testing. It is **not** meant for production use.

## Disclaimer

By using this project, you acknowledge that it is intended for educational purposes only. Any vulnerabilities, secrets, or passwords exposed here are safe to be leaked and should **not** be used in real-world applications.

---

**Happy Hacking!**