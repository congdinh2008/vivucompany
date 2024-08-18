# ViVu Company Management System - Java Hibernate Project

This project demonstrates the usage of Hibernate, a popular Object-Relational Mapping (ORM) framework in Java. It showcases various types of entity relationships, including one-to-one, one-to-many, and many-to-many. Additionally, it provides examples of using native SQL queries, HQL (Hibernate Query Language), and Criteria queries. The project also includes unit tests to ensure the correctness of the implemented functionality.

## Table of Contents

- [Entity Classes](#entity-classes)
- [Entity Relationships](#entity-relationships)
- [Querying Data](#querying-data)
- [Unit Testing](#unit-testing)
  - [Unit Test Packages](#unit-test-packages)
  - [Running the Unit Tests](#running-the-unit-tests)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [License](#license)

## Entity Classes

The project defines the following entity classes:

- `Department`: Represents a department in a company.
- `Employee`: Represents an employee working in a company.
- `EmployeeDetails`: Represents additional details of an employee.
- `Project`: Represents a project that employees can work on.
- `ProjectEmployee`: Represents the relationship between employees and projects.

## Entity Relationships

The project defines entities with different types of relationships:

- One-to-One: Entities that have a single related entity.
- One-to-Many: Entities that have multiple related entities.
- Many-to-Many: Entities that have a many-to-many relationship with other entities.

## Querying Data

The project demonstrates different approaches to querying data using Hibernate:

- Native SQL Queries: Utilize native SQL queries to retrieve data from the database.
- HQL (Hibernate Query Language): Use HQL to write database-agnostic queries.
- Criteria Queries: Employ Criteria queries to dynamically build queries based on specific criteria.

## Unit Testing

To ensure the correctness of the implemented functionality, the project includes unit tests. These tests cover various scenarios and validate the behavior of the Hibernate entities and query methods.

### Unit Test Packages

The unit tests are organized into the following packages:

- `nativequeries`: Contains tests for native SQL queries.
- `hqlqueries`: Contains tests for HQL queries.
- `criteriaqueries`: Contains tests for Criteria queries.

### Running the Unit Tests

To run the unit tests, execute the following command:

```bash
mvn test
```

This command will compile the project, run the unit tests, and generate a report with the test results.

## Technologies

The project is built using the following technologies:

- Java 22[https://www.oracle.com/java/technologies/downloads/]
- Hibernate 7.0.0.Beta1 [https://hibernate.org/orm/]
- Microsoft SQL Server [https://www.microsoft.com/en-us/sql-server/sql-server-downloads]
- JUnit 5 [https://junit.org/junit5/]
- Maven [https://maven.apache.org/]
- Mockito [https://site.mockito.org/]

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository to your local machine.
2. Set up the required database and configure the Hibernate connection properties.
3. Build and run the project using your preferred Java IDE or build tool.
4. Explore the provided examples of entity relationships and query methods.
5. Run the unit tests to verify the correctness of the implemented functionality.

## Contributing

Contributions to this project are welcome. If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request on the project's GitHub repository.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
