# S4.01 — Spring Boot Introduction (Level 2)

## Project Goal & Overview

The goal of this level is to build a **user management API** that stores users in memory using a static list inside the controller. No database is used — the list acts as temporary storage.

This level covers:

- Sending and receiving data in JSON format
- Generating unique identifiers with UUID
- Filtering results with query parameters
- Automated endpoint testing with MockMvc

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

If the user is not found, the API returns `404 Not Found` via a custom `UserNotFoundException` annotated with `@ResponseStatus(HttpStatus.NOT_FOUND)`.

---

## 📌 Class Structure

| Class | Role |
|---|---|
| `UserController` | REST controller with all user endpoints |
| `User` | Model class with `id`, `name`, and `email` |
| `UserNotFoundException` | Custom exception returning 404 |

---

## 🧪 Tests

All tests use `@WebMvcTest` and `MockMvc` to simulate HTTP requests without starting the full application.

| Test | Description |
|---|---|
| `getUsers_returnsEmptyListInitially` | `GET /users` returns an empty array `[]` |
| `createUser_returnsUserWithId` | `POST /users` returns the user with a non-null UUID |
| `getUserById_returnsCorrectUser` | Creates a user, then `GET /users/{id}` returns it correctly |
| `getUserById_returnsNotFoundIfMissing` | `GET /users/{id}` with a non-existing UUID returns `404` |
| `getUsers_withNameParam_returnsFilteredUsers` | Creates two users, `GET /users?name=jo` returns only matching ones |

---

## 🛠 Technologies

- ☕ Java 25
- 🌱 Spring Boot 4.0.5
- 🧪 JUnit 5 + MockMvc
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
