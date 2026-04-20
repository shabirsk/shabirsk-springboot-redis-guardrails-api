#  Backend Guardrails API (Spring Boot + Redis)

##  Overview

This project is a Spring Boot microservice that implements a high-performance backend system with Redis-based guardrails to handle concurrency, prevent spam, and manage bot interactions efficiently.

---

## 🛠 Tech Stack

* Java 17+
* Spring Boot 3.x
* PostgreSQL
* Redis (Docker)
* Maven

---

##  Features

###  Core APIs

* Create Post → `POST /api/posts`
* Add Comment → `POST /api/posts/{postId}/comments`
* Like Post → `POST /api/posts/{postId}/like`

---

### 🔹 Redis Virality Engine

* Bot Reply → +1 point
* Human Like → +20 points
* Human Comment → +50 points
* Stored in Redis:

  ```
  post:{id}:virality_score
  ```

---

### 🔹 Atomic Guardrails (Concurrency Safe)

* **Horizontal Cap** → Max 100 bot replies per post
* **Vertical Cap** → Max depth level = 20
* **Cooldown Cap** → Bot cannot interact repeatedly within 10 minutes

All checks are implemented using Redis atomic operations.

---

### 🔹 Notification Engine

* Uses Redis to store pending notifications
* Throttles notifications (15 min window)
* Scheduled job runs every 5 minutes
* Sends summarized notifications

---

##  How to Run

### 1. Start Docker

```bash
docker-compose up -d
```

### 2. Run Application

Run Spring Boot application from IDE

### 3. Test APIs

Use Postman or any API client

---

##  Key Concepts Implemented

* Redis for distributed state management
* Atomic operations for race condition handling
* Stateless backend architecture
* Event-driven scheduling using Spring

---

##  Notes

* PostgreSQL is used as the source of truth
* Redis acts as a gatekeeper for all rules
* Designed to handle concurrent bot interactions safely

---

## 👨‍💻 Author
shaik shabir

Shaik Shabir
