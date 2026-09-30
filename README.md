# 🚀 SkillMatch — Job Portal & Skill Matching REST API

A **Java Spring Boot REST API** for a job portal that connects job seekers with relevant job opportunities based on their technical skills.

SkillMatch implements **JWT authentication, role-based access control, job management, skill-based matching, validation, exception handling, JPA/Hibernate and MySQL** to provide a secure and structured backend for a recruitment platform.

---

## 📌 Overview

SkillMatch is designed to simplify the job-search process by allowing users to find opportunities relevant to their technical skills.

The backend provides APIs for:

* User registration and authentication
* JWT-based authorization
* Job seeker and recruiter roles
* Job creation and management
* Job searching and retrieval
* Skill-based job matching
* Input validation
* Global exception handling
* API documentation with Swagger/OpenAPI

The project follows a **layered backend architecture** to keep the application modular, maintainable and scalable.

---

## ✨ Features

### 🔐 Authentication & Security

* User registration
* User login
* JWT-based authentication
* BCrypt password hashing
* Stateless authentication
* Role-Based Access Control (RBAC)
* Protected REST APIs
* Bearer token authorization

### 👤 User Roles

#### JOB_SEEKER

* Register and login
* Access available job listings
* Search for relevant opportunities
* Use skill-based matching functionality

#### RECRUITER

* Register and login
* Create job listings
* Update job information
* Delete job listings
* Manage recruitment opportunities

### 💼 Job Management

* Create jobs
* Retrieve all jobs
* Retrieve job by ID
* Update job information
* Delete jobs
* Store company and location information
* Store required technical skills
* Store salary information

### 🧠 Skill Matching

SkillMatch compares the technical skills associated with a candidate against the skills required by a job.

For example:

```text
Candidate Skills
Java
Spring Boot
MySQL
JPA

          ↓

Required Job Skills
Java
Spring Boot
MySQL
JPA
REST API

          ↓

Matched Skills
Java
Spring Boot
MySQL
JPA
```

This provides the foundation for a future intelligent job recommendation system.

---

# 🛠️ Tech Stack

| Technology            | Purpose                        |
| --------------------- | ------------------------------ |
| **Java**              | Backend development            |
| **Spring Boot**       | REST API development           |
| **Spring Security**   | Authentication & authorization |
| **JWT**               | Stateless authentication       |
| **Spring Data JPA**   | Database access                |
| **Hibernate**         | ORM                            |
| **MySQL**             | Relational database            |
| **Maven**             | Dependency management          |
| **Swagger / OpenAPI** | API documentation              |
| **Postman**           | API testing                    |
| **Git & GitHub**      | Version control                |

---

# 🏗️ Architecture

The project follows a **layered architecture**:

```text
                    Client
                      │
                      ▼
              ┌───────────────┐
              │   Controller  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │    Service    │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │   Repository  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │     MySQL     │
              └───────────────┘
```

### Project Layers

```text
com.skillmatch
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
│
└── SkillmatchJobPortalApplication.java
```

### `controller`

Handles incoming HTTP requests and exposes REST endpoints.

### `service`

Contains the application's business logic.

### `repository`

Handles database operations using Spring Data JPA.

### `entity`

Contains JPA entities representing persistent database data.

### `dto`

Contains Data Transfer Objects used for API request and response handling.

### `security`

Contains authentication and authorization related components including JWT processing.

### `config`

Contains application and security configuration.

### `exception`

Provides centralized exception handling and consistent API error responses.

---

# 🔐 Authentication Flow

SkillMatch uses JWT-based stateless authentication.

```text
Register
   │
   ▼
Password Hashing
   │
   ▼
Save User
   │
   ▼
Login
   │
   ▼
Credentials Validation
   │
   ▼
JWT Token Generated
   │
   ▼
Client Sends Bearer Token
   │
   ▼
JWT Authentication Filter
   │
   ▼
Protected API
```

Protected requests use:

```http
Authorization: Bearer <JWT_TOKEN>
```

Passwords are stored using **BCrypt hashing** instead of plain text.

---

# 👥 Role-Based Access Control

The application supports role-based authorization.

| Role         | Access                           |
| ------------ | -------------------------------- |
| `JOB_SEEKER` | Job discovery and skill matching |
| `RECRUITER`  | Job creation and management      |

Authorization is handled using Spring Security and JWT authentication.

---

# 📡 REST API

## 🔑 Authentication

| Method | Endpoint             | Description                       |
| ------ | -------------------- | --------------------------------- |
| `POST` | `/api/auth/register` | Register a new user               |
| `POST` | `/api/auth/login`    | Authenticate user and receive JWT |

## 💼 Jobs

| Method   | Endpoint         | Description             |
| -------- | ---------------- | ----------------------- |
| `GET`    | `/api/jobs`      | Retrieve available jobs |
| `GET`    | `/api/jobs/{id}` | Retrieve a specific job |
| `POST`   | `/api/jobs`      | Create a job            |
| `PUT`    | `/api/jobs/{id}` | Update a job            |
| `DELETE` | `/api/jobs/{id}` | Delete a job            |

> Protected endpoints require a valid JWT Bearer token.

---

# 📚 API Documentation

Swagger/OpenAPI is integrated into the application for interactive API documentation and testing.

After starting the application, open:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger can be used to:

* View available endpoints
* Inspect request and response models
* Authorize using JWT
* Test APIs directly from the browser

---

# 🗄️ Database

SkillMatch uses **MySQL** as its relational database.

The application uses **Spring Data JPA and Hibernate** for persistence and ORM.

### Example User Data

```text
User
├── id
├── name
├── email
├── password
└── role
```

### Example Job Data

```text
Job
├── id
├── title
├── company
├── location
├── description
├── requiredSkills
└── salary
```

---

# 🧪 API Testing

The APIs can be tested using:

* Swagger UI
* Postman

Typical authentication flow:

```text
1. Register
      ↓
2. Login
      ↓
3. Receive JWT
      ↓
4. Authorize with Bearer Token
      ↓
5. Access protected endpoints
```

---

# ⚙️ Getting Started

## Prerequisites

Install the following before running the project:

* Java 17 or higher
* Maven
* MySQL
* IntelliJ IDEA or another Java IDE
* Postman (optional)

---

## 1. Clone the Repository

```bash
git clone https://github.com/dipanshuxsharma/skillmatch-job-portal-backend.git
```

Navigate into the project:

```bash
cd skillmatch-job-portal-backend
```

---

## 2. Create MySQL Database

Create a database in MySQL:

```sql
CREATE DATABASE skillmatch;
```

---

## 3. Configure Database

Open:

```text
src/main/resources/application.properties
```

Configure your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/skillmatch
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace `YOUR_PASSWORD` with your local MySQL password.

> Never commit real passwords, JWT secrets or other sensitive credentials to GitHub.

---

## 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run:

```text
SkillmatchJobPortalApplication.java
```

from IntelliJ IDEA.

The backend will start on:

```text
http://localhost:8080
```

---

# 📂 Project Structure

```text
skillmatch-job-portal-backend/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── skillmatch/
│       │           │
│       │           ├── config/
│       │           ├── controller/
│       │           ├── dto/
│       │           ├── entity/
│       │           ├── exception/
│       │           ├── repository/
│       │           ├── security/
│       │           ├── service/
│       │           │
│       │           └── SkillmatchJobPortalApplication.java
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
└── README.md
```

---

# 🔒 Security

The project implements:

* JWT authentication
* BCrypt password hashing
* Role-based authorization
* Protected API endpoints
* Stateless authentication
* JWT request filtering
* Secure password handling
* Validation and exception handling

---

# 🚨 Exception Handling

The backend includes centralized exception handling to provide consistent API responses.

Common HTTP responses include:

```text
400 → Bad Request
401 → Unauthorized
403 → Forbidden
404 → Not Found
```

This makes API errors easier to understand and handle.

---

# 🎯 Learning Outcomes

Through this project, I gained practical experience in:

* Java backend development
* Spring Boot
* REST API design
* Spring Security
* JWT authentication
* Role-Based Access Control
* BCrypt password hashing
* Spring Data JPA
* Hibernate
* MySQL
* DTO-based API design
* Validation
* Global exception handling
* Swagger/OpenAPI
* Postman API testing
* Layered architecture
* Git & GitHub

---

# 🚀 Future Enhancements

Planned improvements include:

* 🤖 AI-powered job recommendations
* 📄 Resume parsing
* 🎯 Advanced resume-to-job matching
* 📊 Candidate-job compatibility percentage
* 🔔 Job alerts
* 📧 Email notifications
* ❤️ Save/bookmark jobs
* 📝 Job application tracking
* 👨‍💼 Recruiter dashboard
* 📈 Recruitment analytics
* ⚡ Redis caching
* ☁️ Cloud deployment

---

# 👨‍💻 Author

## Dipanshu Sharma

**B.Tech Computer Science Engineering**

Java Backend Developer | Spring Boot | REST APIs | MySQL

### GitHub

https://github.com/dipanshuxsharma

---

# ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.

---

## 📌 Project Status

**🚧 Actively Developed**

The core backend functionality including authentication, authorization, job management and skill-based matching is being developed as part of the SkillMatch project.
