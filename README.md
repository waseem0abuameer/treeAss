# Tree Assignment (JAVA and Spring Boot)

=======================================

## Retrieve Statements

### Description:

This API endpoint allows you to retrieve a list of statements based on various criteria:

1. Account ID: The ID of the account for which you want to retrieve statements.
2. Date Range: The start and end dates of the desired statement period.
3. Amount Range: The minimum and maximum amounts to filter statements by.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Database Diagrams](#database-diagrams)
- [User Roles](#user-roles)

## Technologies Used

This project leverages several key technologies and tools:

- **Java 22.0.2**
- **Spring Boot**
- **Maven 3.9.9**
- **Hibernate**
- **PostgreSQL**
- **Spring Data JPA**
- **Lombok**
- **SonarQube**

## Installation

### Prerequisites

- JDK 11 or later
- Maven 3.6.0 or later
- PostgreSQL (or any other required database)
- Git

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/waseem0abuameer/TreeAssignment.git
   ```
2. Navigate to the project directory:
   ```bash
   cd project-name
   ```
3. Configure the database:
    - Update the `application.properties`
    - Configuration

Before running the project, ensure that your application is configured correctly. Below is the configuration used in the
`application.properties` file:

```properties
spring.application.name=demo
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5433/accountsdb
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=7070
server.servlet.session.timeout=5m
spring.security.user.name=admin
spring.security.user.password=demo@123
sonar.projectKey=tree
sonar.host.url=http://localhost:9001
user.admin=admin
user.admin.passwor=adminPass
user.user=user
passwor.user=userPass

```

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Access the application:
    - Open your browser and navigate to `http://localhost:8080`
6. To Run sonar SonarQube

 ```bash
mvn clean verify sonar:sonar \
-Dsonar.projectKey=TreeAssi \
-Dsonar.projectName='TreeAssi' \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=YOUR_SONARQUBE_TOKEN
```

## API Documentation (accounts)

## The Authoration is Basic Auth

### Username:user

### Password:userPass

### Username:admin

### Password:adminPass

### Add a new Account(Admin)

- **Endpoint:** `/api/accounts`
- **Method:** POST
- **Description:** Add a new account.
- **Body:**
  {
  "accountType": "Current",
  "accountNumber": "12cxcx345671170777"
  }
- ![database-diagrams](/src/img/addacount.png)

- **Endpoint:** `/api/accounts`
- **Method:** GET
- **Description:** Get all account.
- ![database-diagrams](/src/img/getallAcount.png)

## API Documentation (statements)

## The Authoration is Basic Auth

### Username:user

### Password:userPass

### Username:admin

### Password:adminPass

### Get all statements by filter on date and amount(Admin)

- **Endpoint:** `http://localhost:7070/api/statements?fromDate=2016-06-24&toDate=2024-08-24&accountId=4&min=1&max=1000`
- **Method:** GET
- **Description:** get ALL statements admin action.
- ![database-diagrams](/src/img/getAllstatmentAdmin.png)
### Get all statements three months back statement(User)
- **Endpoint:** `http://localhost:7070/api/statements/yourStatements?accountId=4`
- **Method:** GET
- **Description:** Get all statements.
- ![database-diagrams](/src/img/userStm.png)

## Database Diagrams

![database-diagrams](/src/img/Database.png)

## User Roles

### The authenticated users are::

1. Admin : Username: admin & Password: admin
2. User : Username: user & Password: user
   ![user](/src/img/user401.png)
   ![user](/src/img/Screenshot 2024-08-31 at 10.59.54 PM.png)
   ![user](/src/img/tester.png)






