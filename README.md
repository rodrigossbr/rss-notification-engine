<div align="center">
  <img src="https://raw.githubusercontent.com/spring-projects/spring-boot/main/spring-boot-project/spring-boot-docs/src/main/resources/images/logo.png" width="100" alt="Spring Boot Logo">
  <h1>RSS Notification Engine 🚀</h1>
  <p><strong>A high-performance microservice for asynchronous notification orchestration</strong></p>

  [![Java Version](https://img.shields.io/badge/Java-26-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
  [![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](LICENSE)
</div>

---

[Português (Brasil)](README.pt-br.md) | English

The **RSS Notification Engine** is a high-performance microservice developed in **Java 26** and **Spring Boot 4.0.4**. It is designed to orchestrate notification delivery (Email, SMS, WhatsApp and Push) asynchronously and resiliently. The project follows the **Hexagonal Architecture** (Ports and Adapters) to ensure complete decoupling between business logic and infrastructure technologies.

---

## 🛠 Tech Stack

*   **Java 26** (Utilizing Virtual Threads for high concurrency)
*   **Spring Boot 4.0.4**
*   **MongoDB** (Persistence for logs, templates, and auditing with Spring Data - Automatic ID and Timestamp generation)
*   **RabbitMQ** (Message broker with Topic Exchanges)
*   **Docker & Docker Compose** (Container orchestration)
*   **Spring Boot Docker Compose Support** (Automatic lifecycle management)
*   **OpenAPI 3 (Swagger)** (Interactive API documentation)
*   **Lombok** (Boilerplate reduction)
*   **Checkstyle** (Code quality and [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) enforcement)

---

## 🎨 Code Style

The project follows the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). To ensure consistency:

*   **Checkstyle:** The `maven-checkstyle-plugin` is integrated into the build process.
*   **IDE Setup:** It is highly recommended to install the **Checkstyle-IDEA** plugin and import the `google_checks.xml` configuration to get real-time feedback.
*   **Manual Check:** Run `./mvnw checkstyle:check` before committing your changes.

---

The project is structured according to **Hexagonal Architecture** principles:

*   **Core:** Contains the domain models, enums, exceptions, and Use Cases (Business Logic). It is independent of external frameworks.
*   **Ports:** Interfaces that define how the Core interacts with the outside world (Inbound/Outbound).
*   **Adapters In:** REST Controllers for API interaction.
*   **Adapters Out:** Implementations for MongoDB persistence and RabbitMQ messaging.

---

## 📋 Requirements

*   **Java JDK 26**
*   **Maven 3.9+** (or use the provided `./mvnw`)
*   **Docker & Docker Compose**
*   **IDE** (IntelliJ IDEA recommended)

---

## 🚀 Getting Started

### 1. Automatic Infrastructure (Recommended)

This project uses `spring-boot-docker-compose` to automatically manage the infrastructure (MongoDB and RabbitMQ) during development.

1.  Ensure **Docker Desktop** is running.
2.  Run the application using the `local` profile:
    ```bash
    ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
    ```
    *Or via IDE using VM option:* `-Dspring.profiles.active=local`

3.  The application will automatically:
    *   Start MongoDB and RabbitMQ containers.
    *   Wait for them to be healthy.
    *   Open the Swagger UI in your default browser (via `BrowserLauncher`).

### 2. Manual Infrastructure

If you prefer to manage the infrastructure manually:

*   **Start Infrastructure:**
    ```bash
    docker-compose -f infra/docker-compose.yml up -d
    ```
*   **Stop Infrastructure:**
    ```bash
    docker-compose -f infra/docker-compose.yml down
    ```

---

## 📊 Services & Documentation

| Service | Port / URL | Credentials | Description |
| :--- | :--- | :--- | :--- |
| **App API** | `8080` | - | REST API Endpoint |
| **Swagger UI** | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) | - | Interactive API Docs |
| **MongoDB** | `27017` | `admin` / `password` | NoSQL Storage |
| **Rabbit Management** | [http://localhost:15672](http://localhost:15672) | `guest` / `guest` | Message Broker UI |

---

## 🛠 Useful Scripts & Commands

*   **Build the project:**
    ```bash
    ./mvnw clean package
    ```
*   **Run tests:**
    ```bash
    ./mvnw test
    ```
*   **Generate Docker Image:**
    ```bash
    docker build -t rss-notification-engine .
    ```
*   **Check Docker Logs:**
    ```bash
    docker-compose -f infra/docker-compose.yml logs -f
    ```
*   **Run Checkstyle:**
    ```bash
    ./mvnw checkstyle:check
    ```

---

## 🌐 Environment Variables

| Variable | Default (Local) | Description |
| :--- | :--- | :--- |
| `SPRING_PROFILES_ACTIVE` | `local` | Active Spring profile (`local`, `prod`) |
| `SPRING_MONGODB_URI` | `mongodb://admin:password@localhost:27017/notifications_db` | MongoDB connection string |
| `SPRING_RABBITMQ_HOST` | `localhost` | RabbitMQ host |
| `SPRING_RABBITMQ_PORT` | `5672` | RabbitMQ port |
| `SERVER_PORT` | `8080` | Application port |

---

## 📂 Project Structure

```text
src/main/java/br/com/rss/notificationengine/
├── adapters/          # Ports implementations
│   ├── in/            # Entry points (REST Controllers)
│   └── out/           # Infrastructure (MongoDB, RabbitMQ)
├── config/            # Framework configurations (Bean, Mongo, Rabbit)
├── core/              # Business Logic (The Hexagon Heart)
│   ├── domain/        # Entities and value objects
│   ├── ports/         # Inbound/Outbound Interfaces
│   └── usecase/       # Application services/use cases
└── RssNotificationEngineApplication.java # Entry point
```

---

## 🧪 Testing

The project includes unit and integration tests.
*   **Unit Tests:** Focus on Use Cases and Domain logic.
*   **Integration Tests:** Verify adapters and configuration.

Run all tests with:
```bash
./mvnw test
```

---

## 📄 License

This project is licensed under the **MIT License**. See the `LICENSE` file or the details in `pom.xml` for more information.

---

## 🚧 TODOs

- [ ] Implement Consumers (Adapters In) for asynchronous message processing.
- [ ] Add more comprehensive integration tests for RabbitMQ.
- [ ] Implement circuit breaker patterns (Resilience4j).
- [ ] Add support for WhatsApp and Push notification providers.

<div align="center">
  <hr>
  <p>Developed with 💻 and ☕ by <strong>Rodrigo Silveira dos Santos</strong></p>
  <img src="https://img.shields.io/badge/Local-Imbé%2C%20RS-blue?style=flat-square&logo=googlemaps&logoColor=white" alt="Local">
  <a href="mailto:rodrigoss.br%40gmail.com" target="_blank">
    <img src="https://img.shields.io/badge/Email-rodrigoss.br%40gmail.com-green?style=flat-square&logo=gmail&logoColor=white" alt="Email">
  </a>
  <p>© 2026 All rights reserved.</p>
</div>