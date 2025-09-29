# Inditex Prices API (Technical Test)

Spring Boot REST API to retrieve the **applicable price** of a product for a given brand and date.  
Built following **Hexagonal Architecture (Ports & Adapters)**, **API-First**, **TDD**, with **H2 in-memory DB**, **Swagger UI**, **unit + E2E tests**, **Docker**, **JaCoCo coverage**, and **Conventional Commits**.

---

## 📑 Table of Contents

- [Technology Stack](#️-technology-stack)
- [Architecture: Hexagonal (Ports & Adapters)](#️-architecture-hexagonal-ports--adapters)
- [Project Structure](#-project-structure)
- [Quickstart](#-quickstart)
- [Database (H2)](#database-h2)
- [API (OpenApi/Swagger)](#-api-openapiswagger)
- [Testing & Coverage](#-testing--coverage)
- [Design Notes](#design-notes)
- [Future Improvements](#future-improvements)

---

## 🛠️ Technology Stack

| Component         | Version         |
|------------------|----------------|
| Java             | 21              |
| Spring Boot      | 3.x             |
| Maven            | 3.9.x           |
| H2 Database      | 2.x             |
| JUnit            | 5.x             |
| JaCoCo           | Latest          |
| springdoc-openapi| 2.6.x           |
| Docker           | Latest          |

---

## 🏗️ Architecture: Hexagonal (Ports & Adapters)

```
+-------------------+
|   REST Controller |  <-- Adapter Inbound
+-------------------+
         |
         v
+-------------------+
| Application Layer |  <-- Use Cases (e.g. GetPriceUseCase)
+-------------------+
         |
         v
+-------------------+
|    Domain Model   |  <-- Pure Java, business rules
+-------------------+
         ^
         |
+-------------------+
| Persistence Layer |  <-- Adapter Outbound (JPA/H2)
+-------------------+
```

---

## 📦 Project Structure

```
src/
  main/
    java/com/inditex/prices/
      domain/
      application/
      infrastructure/
        rest/
        persistence/
        rest/config/
    resources/
      application.yml
      schema.sql
      data.sql
      static/openapi.yaml
```

---

## 🔥 Quickstart

1. **Build & Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
2. **Docker**
   ```bash
   docker build -t prices-api .
   docker run -p 8080:8080 prices-api
   ```
3. **From jar**
   ```bash
    mvn clean package
    java -jar target/prices-api-0.0.1-SNAPSHOT.jar
   ```   

---

## 🗄️ Database (H2)

- **H2 in-memory database** initialized via `schema.sql` and `data.sql`.
- **Primary Key**: composite `(BRAND_ID, PRICE_LIST, PRODUCT_ID)`  
  > ⚠️ No surrogate `ID` column. This matches the functional requirements.

---
## 📖 API (OpenApi/Swagger)

**Access the API:**
   - Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
   - OpenAPI Spec: [http://localhost:8080/openapi.yaml](http://localhost:8080/openapi.yaml)

**Example endpoint:**
 ```http
GET /prices?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00
```


**Request:**
```http
GET /prices?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00
```

**Response:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```
Errors:
- `404` → Not prices found
- `400` → Invalid value for param

---

## 🧪 Testing & Coverage
**Run tests:**
```bash
mvn test
```
**Generate coverage report:**
```bash
mvn verify
```

- **Unit tests** for domain logic (pure Java)
- **Application tests** for use cases
- **Integration tests** for REST controllers (MockMvc)
- **E2E tests** for full request/response
- **Coverage**: JaCoCo report at `target/site/jacoco/index.html`

---

## 📝 Design Notes

- **Composite PK** chosen instead of surrogate ID to respect business requirements.  
- **Conflict resolution**: if multiple tariffs match, the one with highest `PRIORITY` is applied.  
- **Monetary values**: `BigDecimal` to avoid precision issues.  
- **API-First**: OpenAPI spec is the contract.  
- **Price selection with PriceSelector**: a dedicated domain service decides the applicable price when multiple candidates overlap, always selecting the one with the highest priority. This keeps business logic centralized, testable, and independent from infrastructure.

---

## 🚀 Future Improvements

- Input validation with `jakarta.validation`.  
- Caching price lookups.  
- CI/CD pipeline (build + tests + coverage + Docker publish).  
- Docker registry publish.
