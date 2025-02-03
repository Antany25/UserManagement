
# User Management API

This project provides a **Spring Boot-based User Management System** with authentication, role management, and Swagger documentation.

## Features
- User registration with hashed passwords
- Authentication and role-based authorization
- H2 database integration
- Swagger UI for API documentation
- IP and country tracking during registration

## Prerequisites
Ensure you have the following installed before running the project:
- **Java 17+**
- **Maven** (for dependency management)
- **IntelliJ IDEA / Eclipse** (recommended IDEs)
- **Postman or cURL** (for API testing)

## How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-repo/user-management.git
cd user-management
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```
or
```bash
java -jar target/usermanagement-0.0.1-SNAPSHOT.jar
```

### 4. Access H2 Database
- Open `http://localhost:2525/h2-console`
- JDBC URL: `jdbc:h2:file:C:/Users/Nxgen/test`
- Username: `sa`
- Password: `sa`

### 5. Access Swagger UI
- Open `http://localhost:2525/swagger-ui.html`

### 6. API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/users/` | Root endpoint |
| `POST` | `/users/register` | Register a new user |
| `POST` | `/users/validate?email=&password=` | Validate user credentials |
| `GET` | `/users/all` | Get all registered users (Admin Only) |
| `DELETE` | `/users/delete?email=` | Delete a user (Admin Only) |

### 7. Environment Configuration
Modify `application.properties` for different database setups (e.g., in-memory H2 or external DB).

### 8. Logs
- Logs are stored and configured via `application.properties`
- Hibernate SQL queries can be viewed in the console

### 9. Dockerization (Optional)
To containerize the application:
```bash
docker build -t user-management .
docker run -p 2525:2525 user-management
```

---

This README will help any developer or user quickly understand and run the application. 🚀