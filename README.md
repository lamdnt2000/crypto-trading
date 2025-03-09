# **Crypto Trading System**


This is a Spring Boot-based crypto trading system using an H2 in-memory database. It allows users to trade ETH/USDT and BTC/USDT pairs, track their transactions, and view wallet balances. The system aggregates real-time prices from Binance and Huobi to ensure the best available trading price.

---

## **Table of Contents**
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Database Structure](#database-structure)
4. [Project Structure](#project-structure)
5. [ShedLock Configuration](#config-shedlock)
6. [Setup and Installation](#setup-and-installation)
---

## **Features**
- 🔄 **Real-Time Price Aggregation**: Fetch best aggregated prices from multiple exchanges.
- 💱 **Crypto Trading**: Executes BUY/SELL orders based on the latest market prices.
- 📊 **Historical Trade Data**: Records and tracks all trading transactions.
- 💰 **Wallet System**: Displays real-time balance of crypto assets.
- ⚙️ **Configurable**: Easy-to-manage settings for system like API integration, scheduling.

---

## **Technologies Used**
- **Java 17**: Modern, high-performance language for robust backend development.
- **Spring Boot**: Framework for building scalable RESTful APIs with dependency injection.
- **JPA (Hibernate)**: ORM for seamless database interaction and entity management.
- **H2 Database**: Lightweight, in-memory database for rapid development and testing.
- **Spring Scheduler**: Enables periodic task execution for real-time data updates.
- **Feign Clients**: Declarative HTTP client for seamless external API communication.
- **Distributed Scheduler**: Ensures efficient and scalable job execution across instances.
- **MapStruct**: Automates DTO-to-entity mapping, reducing boilerplate code.
- **Lombok**: Simplifies code with auto-generated getters, setters, and constructors.
---

## **Database Structure**
![Database diagram](https://raw.githubusercontent.com/lamdnt2000/crypto-trading/refs/heads/main/src/main/resources/db/db-diagram.png)
--
## **Project Structure**
```plaintext
Directory structure:
└── lamdnt2000-crypto-trading/
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/
        │   │       └── lamdnt/
        │   │           └── cryptotrading/
        │   │               ├── configuration/           #Bean configuration 
        │   │               ├── connector/               #Feign Client
        │   │               ├── controller/              #RestAPI Controller
        │   │               ├── entity/                  #JPA Entity
        │   │               ├── mapper/                  #Mapstruct for mapping object
        │   │               ├── model/                   #Request, Response or Feign Client Object
        │   │               ├── repository/              #Repository Layer
        │   │               ├── secheduler/              #Scheduler handler
        │   │               ├── service/                 #Bussiness logic service layer
        │   │               └── utils/                   #Common util
        │   └── resources/
        │       ├── application.properties               #Configuration properties
        │       └── db/
        │           ├── import_data.sql                  #Init sample data for h2
        │           └── schema.sql                       #Init table for h2
        └── test/ 
```
---

## **ShedLock Configuration**
1. Why using **shedlock**? <br>
   **ShedLock** ensures that only **one instance** of the application executes the price aggregation job, preventing duplicate execution in a distributed environment.
2. Benefit:<br>
   - Prevents duplicate execution across multiple instances. 
   - Ensures data consistency by avoiding race conditions. 
   - Supports failover—if one instance crashes, another can take over.
3. Configuration:
```
#Config time interval follow cron format. Example: 10s.
scheduler.task.fetch-symbols-price.cron=*/10 * * * * *
#Config lock name record in db.
scheduler.task.fetch-symbols-price.name=priceAggregationTask
#Maximum time to hold lock. Example: 7s.
scheduler.task.fetch-symbols-price.lock.atMostFor=PT7S
#Minimun time to hold lock before another instance  can acquire. Example: 3s.
scheduler.task.fetch-symbols-price.lock.atLeastFor=PT3S
#Default lock time for unspecified tasks.
scheduler.lock.default.atMostFor=PT10S
```


---

## **Setup and Installation**

### **Prerequisites**
- **Java 17** installed.
- **Gradle** for dependency management.

---

### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/lamdnt2000/crypto-trading.git
   ```
2. Configure the application:
    - Update the database credentials in `application.properties`
      ```bash
        spring.datasource.username=sa
        spring.datasource.password=password
      ```
    - Update H2 database console endpoint:
      ```bash
        spring.h2.console.path=/h2-console
      ```
    - Update Swagger API endpoint:
      ```bash
         springdoc.swagger-ui.path=/swagger-ui
      ```
3. Build and run the project:<br>
Executing by java command:
      ```bash
      ./gradlew spotlessApply clean build 
      java -jar build/libs/crypto-trading-0.0.1-SNAPSHOT.jar
      ```
   Executing by gradlew command:
   ```bash
    ./gradlew spotlessApply clean 
    ./gradlew bootRun
   ```
4. Access the database:
    - http://localhost:8080/h2-console
   
4. Access Swagger API:
    - http://localhost:8080/swagger-ui

5. Send API request:
    - Host: http://localhost:8080
    - Endpoint: refer to Swagger document: http://localhost:8080/swagger-ui

----

