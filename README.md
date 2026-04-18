# S4.01 — Spring Boot Introduction (Level 3)

## Project Goal & Overview

The goal of this level is to refactor the user management API into a **layered architecture**, separating responsibilities across controller, service, and repository layers.

This level covers:

- Applying the Repository pattern with an interface and in-memory implementation
- Implementing a Service layer to centralize business logic
- Using Spring dependency injection with `@Service` and `@Repository`
- Integration testing with `@SpringBootTest` and `MockMvc`
- Unit testing the service layer with Mockito

---

## 📌 Model

The `User` class has the following properties:

| Field | Type | Description |
|---|---|---|
| `id` | `UUID` | Unique identifier, auto-generated |
| `name` | `String` | User's name |
| `email` | `String` | User's email |

---

## 📌 Endpoints

| Method | URL | Description |
|---|---|---|
| `GET` | `/users` | Returns the full list of users |
| `GET` | `/users?name=ada` | Filters users by name (case-insensitive) |
| `GET` | `/users/{id}` | Returns a specific user by UUID |
| `POST` | `/users` | Creates a new user with auto-generated UUID |

### POST /users — Example Request

```json
{
  "name": "Ada Lovelace",
  "email": "ada@example.com"
}
```

### POST /users — Example Response

```json
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "Ada Lovelace",
  "email": "ada@example.com"
}
```

### GET /users/{id} — Error Case

If the user is not found, the API returns `404 Not Found` via a custom `UserNotFoundException`.

---

## 📌 Class Structure

| Class | Role |
|---|---|
| `UserController` | REST controller, delegates to service |
| `UserService` | Interface defining business use cases |
| `UserServiceImpl` | Service implementation annotated with `@Service` |
| `UserRepository` | Interface defining data access operations |
| `InMemoryUserRepository` | In-memory implementation annotated with `@Repository` |
| `User` | Model class with `id`, `name`, and `email` |
| `UserNotFoundException` | Custom exception returning 404 |

---

## 🧪 Tests

### Integration Tests — `UserControllerTest`

Uses `@SpringBootTest` and `MockMvc` to test the full application stack.

| Test | Description |
|---|---|
| `getUsers_returnsEmptyListInitially` | `GET /users` returns an empty array `[]` |
| `createUser_returnsUserWithId` | `POST /users` returns the user with a non-null UUID |
| `getUserById_returnsCorrectUser` | Creates a user, then `GET /users/{id}` returns it correctly |
| `getUserById_returnsNotFoundIfMissing` | `GET /users/{id}` with a non-existing UUID returns `404` |
| `getUsers_withNameParam_returnsFilteredUsers` | Creates two users, `GET /users?name=jo` returns only matching ones |

### Unit Tests — `UserServiceImplTest`

Uses Mockito to test the service layer in isolation.

| Test | Description |
|---|---|
| `createUser_savesAndReturnsUser` | Verifies user is created and saved via repository |
| `getUserById_returnsUser` | Returns correct user when found |
| `getUserById_throwsExceptionWhenNotFound` | Throws `UserNotFoundException` when user doesn't exist |
| `getAllUsers_returnsList` | Returns all users from repository |
| `getUsersByName_returnsFilteredList` | Returns filtered users by name |

---

## 🛠 Technologies

- ☕ Java 25
- 🌱 Spring Boot 4.0.5
- 🧪 JUnit 5 + MockMvc + Mockito
- 🏗️ Maven
- 🐙 Git & GitHub

---

## 🚀 Installation and Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Dani87dev/S4T1_Spring_Boot_Introduction.git
   ```

2. **Navigate to the project folder:**
   ```bash
   cd S4T1_Spring_Boot_Introduction
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   The server will start on port **9000**.

4. **Run the tests:**
   ```bash
   mvn test
   ```
