# 📚 Library System API

A RESTful API for managing a simple library system, built with **Java 17**, **Spring Boot**, **Maven**, and **PostgreSQL**. This project supports containerization with Docker, orchestration with Kubernetes, and CI/CD via GitHub Actions.

---

## ✅ Features

- Register new **Borrowers** and **Books**
- View all available books
- Borrow and return books
- Prevent duplicate borrowing of the same book instance
- Validation, exception handling, and logging
- Dockerized for environment consistency
- Kubernetes-ready deployment
- Unit and integration tests
- CI/CD via GitHub Actions

---

## 🚀 Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Docker
- Kubernetes
- GitHub Actions (CI/CD)
- JUnit & Mockito

## 🛠 Setup Instructions

### 1. Run Locally

```bash
# Build project
mvn clean install

# Run with Spring Boot
mvn spring-boot:run

# Build Docker image
docker build -t library-system-api .

# Run Docker container
docker run -p 8080:8080 library-system-api

⚙️ API Endpoints
Method	Endpoint	Description
POST	/api/borrowers	Register a new borrower
POST	/api/books	Register a new book
GET	/api/books	List all books
POST	/api/borrow/{bookId}	Borrow a book by ID
POST	/api/return/{bookId}	Return a borrowed book

🧪 Testing
# Run tests
mvn test
Unit tests exist for:
BookServiceTest
BorrowServiceTest
LibraryControllerTest

🐳 Dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/library-system-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

📝 Assumptions
Each Book entry represents a physical copy (unique id)
ISBN uniquely identifies a book’s identity (title + author)
One borrower can borrow only available books
Return is only allowed if the book is currently borrowed

🔐 Environment Config
You can configure environment-specific settings using application-{env}.properties files.
Use Docker environment variables or Kubernetes secrets to pass configuration like database URL, username, password, etc.



