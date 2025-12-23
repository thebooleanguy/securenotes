# 📝 Secure Notes Backend API

A Spring Boot REST API that allows users to manage personal notes securely using JWT-based authentication and role-based authorization.  
Built as a mini backend project to refresh and demonstrate core backend, security, and database concepts.

---

## 🚀 Features

- User authentication with JWT
- Role-based authorization (ROLE_USER, ROLE_ADMIN)
- Secure CRUD for notes
- Each note belongs to its authenticated owner
- Admin can view all notes
- Stateless API (no sessions)
- Layered architecture (Controller -> Service -> Repository -> DB)

---

## 🛠 Tech Stack

- Java 25
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA / Hibernate
- H2
- Maven

---

## 🧱 Architecture

Client -> Security Filter (JWT Validation) -> Controller -> Service -> Repository -> Database

- Controller: REST endpoints
- Service: Business logic + SecurityContext usage
- Repository: JPA DB access
- JWT Filter: Validates token & sets authentication
- SecurityContext: Holds logged-in user info

---

## 📦 Entities

### User
- id
- username
- password (BCrypt)
- role

### Note
- id
- content
- user (Many-to-One)

Relationship: User 1 : N Note

---

## 🔐 Authentication Flow

1. User logs in with username/password
2. Server validates credentials
3. JWT token is generated and returned
4. Client sends token in Authorization header
5. JWT filter validates token for each request
6. Authenticated user is available via SecurityContext

---

## 🔑 API Endpoints

### Auth
Method: POST  
Endpoint: /api/auth/login  
Description: Login and get JWT token

### Notes (USER / ADMIN)
Method: POST  
Endpoint: /api/notes  
Description: Create a note for logged-in user

Method: GET  
Endpoint: /api/notes  
Description: Get notes of logged-in user

### Admin Only
Method: GET  
Endpoint: /api/notes/admin  
Description: Get all notes

---

## 🧪 Testing with curl

### Login as USER

curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"user123"}'

### Login as ADMIN

curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

Store tokens:

USER_TOKEN=PASTE_USER_TOKEN  
ADMIN_TOKEN=PASTE_ADMIN_TOKEN

### Create Note (USER)

curl -X POST http://localhost:8080/api/notes \
  -H "Authorization: Bearer $USER_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"content":"My first secure note"}'

### Get My Notes (USER)

curl http://localhost:8080/api/notes \
  -H "Authorization: Bearer $USER_TOKEN"

### Get All Notes (ADMIN)

curl http://localhost:8080/api/notes/admin \
  -H "Authorization: Bearer $ADMIN_TOKEN"

### Unauthorized (No Token)

curl http://localhost:8080/api/notes

Expected: 401 Unauthorized

### Forbidden (USER accessing admin)

curl http://localhost:8080/api/notes/admin \
  -H "Authorization: Bearer $USER_TOKEN"

Expected: 403 Forbidden

---

## 🧠 Key Concepts Demonstrated

- Authentication vs Authorization
- JWT stateless security
- Spring Security filters
- SecurityContext usage
- RESTful API design
- Layered architecture
- JPA relationships
- Lazy fetching
- Secure resource ownership

---

## ▶️ Running the App

mvn spring-boot:run

App runs at:  
http://localhost:8080

---

## 🌱 Possible Improvements

- Global exception handling (ControllerAdvice)
- Refresh tokens
- Pagination & sorting
- API documentation (Swagger/OpenAPI)
- Unit & integration tests

---

## 👤 Author

Tharindu Abeyrathna  
Built as a mini project to refresh backend and security fundamentals for interviews.

---

## 📄 License

MIT License
