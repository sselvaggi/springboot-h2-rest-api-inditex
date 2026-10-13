# Inditex Pricing Service

A production-ready, minimalist Spring Boot REST API built with **Hexagonal Architecture**, **Modern Java 21**, and an in-memory **H2 Database** using `JdbcTemplate`.

## 🏗️ Architectural Overview

The application strictly separates business logic from external frameworks and persistence mechanisms to ensure high maintainability, testability, and clear boundaries:

```
com.challenge.inditex
├── WebApplication.java                  # Main Spring Boot bootstrap
├── domain/                              # Pure business models & DTOs
│   ├── Tariff.java
│   └── TariffSearchDTO.java
├── application/                         # Use case layers / Business logic
│   └── service/
│       └── TariffService.java
└── infrastructure/                      # External adapters (Web, DB)
    ├── inbound/
    │   └── rest/
    │       └── TariffController.java
    └── outbound/
        └── persistence/
            └── TariffRepository.java
```

## 🚀 Tech Stack & Design Choices

* **Java 21**: Leveraging immutable `record` components for data transfer objects and domain models.
* **Spring Boot**: Lightweight web configuration utilizing `spring-boot-starter-web`, `spring-boot-starter-jdbc`, and `spring-boot-starter-validation`.
* **H2 In-Memory Database**: Seeded automatically via SQL scripts matching the exact specification.
* **Idiomatic Error Handling**:
    * Valid requests returning no prices resolve cleanly to **`404 Not Found`** via Spring's `ResponseEntity.of(Optional)`.
    * Parameter validation and boundary checks are managed via Jakarta validation (`@Valid`).

## 🕹️ Getting Started & Execution

### Prerequisites

* Java 21 JDK installed.
* Maven (or use the included Maven wrapper `./mvnw`).

### 1. Build the Project

```bash
mvn clean install
```

### 2. Run the Application

```bash
mvn spring-boot:run
```

The application will start on port `8080`.

## 📡 API Usage Example

You can query the pricing endpoint via `cURL`:

```bash
curl --url 'http://localhost:8080/api/tariff/search' \
-H 'Content-Type: application/json' \
--data-raw '{"brandId":1, "productId":35455, "datetime":"2020-06-14-10.00.00"}'
```

### Response Example (`200 OK`)

```json
{
  "brandId": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "priceList": 1,
  "productId": 35455,
  "priority": 0,
  "price": 35.50,
  "currency": "EUR"
}
```

## 🧪 Running Tests

The test suite validates all functional scenarios alongside negative validation edge cases using Spring's `MockMvc`:

```bash
mvn test
```

### Test Scenarios Covered:

1. **Test 1:** June 14 at 10:00 (Product 35455, Brand 1) $\rightarrow$ **Price List 1 (35.50 EUR)**
2. **Test 2:** June 14 at 16:00 (Product 35455, Brand 1) $\rightarrow$ **Price List 2 (25.45 EUR)** *(Higher priority wins)*
3. **Test 3:** June 14 at 21:00 (Product 35455, Brand 1) $\rightarrow$ **Price List 1 (35.50 EUR)**
4. **Test 4:** June 15 at 10:00 (Product 35455, Brand 1) $\rightarrow$ **Price List 3 (30.50 EUR)**
5. **Test 5:** June 16 at 21:00 (Product 35455, Brand 1) $\rightarrow$ **Price List 4 (38.95 EUR)**
6. **Negative Case (Not Found):** Out-of-range date query $\rightarrow$ **`404 Not Found`**
7. **Validation Case (Bad Request):** Missing or negative fields $\rightarrow$ **`400 Bad Request`**