# RSS Notification Engine 🚀

O **RSS Notification Engine** é um microserviço de alta performance desenvolvido em **Java 25/Spring Boot 4**, projetado para orquestrar o envio de notificações (E-mail, SMS, WhatsApp e Push) de forma assíncrona e resiliente. O projeto utiliza **Arquitetura Hexagonal** para garantir o desacoplamento entre a lógica de negócio e as tecnologias de infraestrutura.

## 🛠 Tecnologias Principais
* **Java 26** (Utilizando Virtual Threads para alta concorrência)
* **Spring Boot 4.0.4**
* **MongoDB** (Persistência de logs e auditoria com Spring Data)
* **RabbitMQ** (Broker de mensageria com Topic Exchanges)
* **Docker & Docker Compose** (Orquestração de containers)
* **OpenAPI 3 (Swagger)** (Documentação interativa)

---

## 🏗 Arquitetura
O projeto segue o padrão **Hexagonal (Ports and Adapters)**:
* **Core:** Contém o domínio e os casos de uso (Business Logic), independente de frameworks.
* **Adapters In:** Entrada via API REST (Controllers) e futuramente Consumers.
* **Adapters Out:** Saída para MongoDB e RabbitMQ.

---

## 🐳 Ambiente de Desenvolvimento

Para facilitar o desenvolvimento, a infraestrutura é automatizada. O Spring Boot gerencia o ciclo de vida dos containers localmente.

### 🚀 Como rodar
1. Certifique-se de que o **Docker Desktop** está rodando.
2. Execute a aplicação via IDE (IntelliJ) usando o perfil `local`:
   ```bash
   -Dspring.profiles.active=local
   ```
3. Automação: Ao iniciar, o Spring subirá o MongoDB e o RabbitMQ automaticamente. Assim que o serviço estiver pronto, o Swagger será aberto automaticamente no seu navegador padrão.

### 📊 Serviços e Documentação

| Serviço | Porta / URL | Credenciais | Descrição |
| :--- | :--- | :--- | :--- |
| **Swagger UI** | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) | - | Teste e documentação da API. |
| **MongoDB** | `27017` | `admin` / `password` | Banco NoSQL para logs de envio. |
| **Rabbit Management**| [http://localhost:15672](http://localhost:15672) | `guest` / `guest` | Painel de controle das filas. |

## 🔍 Comandos Úteis (Via Terminal)

Caso prefira gerenciar a infraestrutura manualmente a partir da **raiz do projeto**:

* **Subir infraestrutura:**
  ```bash
  docker-compose -f infra/docker-compose.yml up -d
  ```
* **Logs em tempo real:**
  ```bash
  docker-compose -f infra/docker-compose.yml logs -f
  ```
* **Parar e limpar ambiente:**
  ```bash
  docker-compose -f infra/docker-compose.yml down
  ```