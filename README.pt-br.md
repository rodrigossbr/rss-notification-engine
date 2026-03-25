<div align="center">
  <img src="https://raw.githubusercontent.com/spring-projects/spring-boot/main/spring-boot-project/spring-boot-docs/src/main/resources/images/logo.png" width="100" alt="Spring Boot Logo">
  <h1>RSS Notification Engine 🚀</h1>
  <p><strong>Microsserviço de alta performance para orquestração de notificações assíncronas</strong></p>

  [![Java Version](https://img.shields.io/badge/Java-26-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
  [![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](LICENSE)
</div>

---

Português (Brasil) | [English](README.md)

O **RSS Notification Engine** é um microsserviço de alta performance desenvolvido em **Java 26** e **Spring Boot 4.0.4**. Ele foi projetado para orquestrar a entrega de notificações (E-mail, SMS, WhatsApp e Push) de forma assíncrona e resiliente. O projeto segue a **Arquitetura Hexagonal** (Ports and Adapters) para garantir o desacoplamento completo entre a lógica de negócio e as tecnologias de infraestrutura.

---

## 🛠 Tecnologias

*   **Java 26** (Utilizando Virtual Threads para alta concorrência)
*   **Spring Boot 4.0.4**
*   **MongoDB** (Persistência para logs, templates e auditoria com Spring Data)
*   **RabbitMQ** (Message broker com Topic Exchanges)
*   **Docker & Docker Compose** (Orquestração de containers)
*   **Spring Boot Docker Compose Support** (Gerenciamento automático de ciclo de vida)
*   **OpenAPI 3 (Swagger)** (Documentação interativa da API)
*   **Lombok** (Redução de código boilerplate)

---

## 🏗 Arquitetura

O projeto está estruturado de acordo com os princípios da **Arquitetura Hexagonal**:

*   **Core:** Contém os modelos de domínio, enums, exceções e Casos de Uso (Lógica de Negócio). É independente de frameworks externos.
*   **Ports:** Interfaces que definem como o Core interage com o mundo externo (Inbound/Outbound).
*   **Adapters In:** Controllers REST para interação com a API.
*   **Adapters Out:** Implementações para persistência em MongoDB e mensageria com RabbitMQ.

---

## 📋 Requisitos

*   **Java JDK 26**
*   **Maven 3.9+** (ou use o `./mvnw` fornecido)
*   **Docker & Docker Compose**
*   **IDE** (IntelliJ IDEA recomendado)

---

## 🚀 Primeiros Passos

### 1. Infraestrutura Automática (Recomendado)

Este projeto utiliza o `spring-boot-docker-compose` para gerenciar automaticamente a infraestrutura (MongoDB e RabbitMQ) durante o desenvolvimento.

1.  Certifique-se de que o **Docker Desktop** esteja rodando.
2.  Execute a aplicação usando o perfil `local`:
    ```bash
    ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
    ```
    *Ou via IDE usando a opção VM:* `-Dspring.profiles.active=local`

3.  A aplicação irá automaticamente:
    *   Iniciar os containers do MongoDB e RabbitMQ.
    *   Aguardar até que estejam saudáveis.
    *   Abrir o Swagger UI no seu navegador padrão (via `BrowserLauncher`).

### 2. Infraestrutura Manual

Se preferir gerenciar a infraestrutura manualmente:

*   **Iniciar Infraestrutura:**
    ```bash
    docker-compose -f infra/docker-compose.yml up -d
    ```
*   **Parar Infraestrutura:**
    ```bash
    docker-compose -f infra/docker-compose.yml down
    ```

---

## 📊 Serviços & Documentação

| Serviço | Porta / URL | Credenciais | Descrição |
| :--- | :--- | :--- | :--- |
| **App API** | `8080` | - | Endpoint da API REST |
| **Swagger UI** | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) | - | Docs Interativas da API |
| **MongoDB** | `27017` | `admin` / `password` | Armazenamento NoSQL |
| **Rabbit Management** | [http://localhost:15672](http://localhost:15672) | `guest` / `guest` | Interface do Message Broker |

---

## 🛠 Scripts & Comandos Úteis

*   **Build do projeto:**
    ```bash
    ./mvnw clean package
    ```
*   **Executar testes:**
    ```bash
    ./mvnw test
    ```
*   **Gerar Imagem Docker:**
    ```bash
    docker build -t rss-notification-engine .
    ```
*   **Verificar Logs do Docker:**
    ```bash
    docker-compose -f infra/docker-compose.yml logs -f
    ```

---

## 🌐 Variáveis de Ambiente

| Variável | Padrão (Local) | Descrição |
| :--- | :--- | :--- |
| `SPRING_PROFILES_ACTIVE` | `local` | Perfil Spring ativo (`local`, `prod`) |
| `SPRING_MONGODB_URI` | `mongodb://admin:password@localhost:27017/notifications_db` | String de conexão MongoDB |
| `SPRING_RABBITMQ_HOST` | `localhost` | Host do RabbitMQ |
| `SPRING_RABBITMQ_PORT` | `5672` | Porta do RabbitMQ |
| `SERVER_PORT` | `8080` | Porta da aplicação |

---

## 📂 Estrutura do Projeto

```text
src/main/java/br/com/rss/notificationengine/
├── adapters/          # Implementações de Ports
│   ├── in/            # Pontos de entrada (Controllers REST)
│   └── out/           # Infraestrutura (MongoDB, RabbitMQ)
├── config/            # Configurações do Framework (Bean, Mongo, Rabbit)
├── core/              # Lógica de Negócio (O Coração do Hexágono)
│   ├── domain/        # Entidades e objetos de valor
│   ├── ports/         # Interfaces Inbound/Outbound
│   └── usecase/       # Serviços da aplicação/casos de uso
└── RssNotificationEngineApplication.java # Ponto de entrada
```

---

## 🧪 Testes

O projeto inclui testes unitários e de integração.
*   **Testes Unitários:** Foco nos Casos de Uso e lógica de Domínio.
*   **Testes de Integração:** Verificam os adaptadores e a configuração.

Execute todos os testes com:
```bash
./mvnw test
```

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License**. Veja o arquivo `LICENSE` ou os detalhes no `pom.xml` para mais informações.

---

## 🚧 TODOs

- [ ] Implementar Consumidores (Adapters In) para processamento assíncrono de mensagens.
- [ ] Adicionar testes de integração mais abrangentes para o RabbitMQ.
- [ ] Implementar padrões de circuit breaker (Resilience4j).
- [ ] Adicionar suporte para provedores de notificação WhatsApp e Push.


<div align="center">
  <hr>
  <p>Desenvolvido com 💻 e ☕ por <strong>Rodrigo Silveira dos Santos</strong></p>
  <img src="https://img.shields.io/badge/Local-Imbé%2C%20RS-blue?style=flat-square&logo=googlemaps&logoColor=white" alt="Local">
  <a href="mailto:rodrigoss.br%40gmail.com" target="_blank">
    <img src="https://img.shields.io/badge/Email-rodrigoss.br%40gmail.com-green?style=flat-square&logo=gmail&logoColor=white" alt="Email">
  </a>
  <p>© 2026 Todos os direitos reservados.</p>
</div>
