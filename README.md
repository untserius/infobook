# Infobook Student Management System

Infobook is a comprehensive student management system designed to streamline administrative tasks related to student information management. Whether you're managing a small educational institution or a large university, Infobook provides the tools you need to efficiently organize student data, track academic progress, and facilitate communication between students, faculty, and administrators.

## Project Flow

### 1. Generate a project setup (.zip) file form Spring Initializer.

### 3. Open MySQL, and make an DB for the project.

### 2. Configure the application.properties for connection to that DB.

```
spring.application.name=infobook

spring.datasource.url=jdbc:mysql://localhost:3306/infobook_db
spring.datasource.username=root
spring.datasource.password=test
spring.jpa.hibernate.ddl-auto=update
```

### 4. Entity

Create a **entity** package, inside it create a class called `Student`.

![Screenshot of entity class.](/infobook_backend/src/main/resources/static/images/entity.png)

### 5. Repository

Create a **repository** package, inside it create a class called `StudentRepository`.

![Screenshot of student_repository class.](/infobook_backend/src/main/resources/static/images/student_repository.png)

By extending JpaRepository, StudentRepository inherits methods for performing common database operations such as saving, updating, deleting, and querying `Student` entities.

You don't need to provide implementations for these methods; Spring Data JPA automatically generates them at runtime based on the method signatures.

### 6. Payload

Create a **payload** package, inside it create a class called `StudentDto`.

![Screenshot](/infobook_backend/src/main/resources/static/images/student_dto.png)

**_So, why use DTOs?_**

- DTOs help reduce the amount of data transferred over the network or between different layers of your application. Since they contain only the necessary information.

- DTOs encapsulate the data being transferred, providing a layer of security by hiding sensitive or unnecessary information that should not be exposed outside.

- By using DTOs, you can decouple different parts of your application. This makes it easier to modify or extend the data structure without affecting other parts of the application. For example, if you decide to add or remove fields from the Student object, you can update the corresponding DTOs without impacting the entire system.

### 6. Service

Create a **service** package, inside it create a class called `StudentService`.

![Screenshot](/infobook_backend/src/main/resources/static/images/student_service.png)

Overall, this interface defines a contract for a service responsible for creating student records based on DTO input.

#### 6.1 Service Implementations

Create a **impl** package inside the **service** package and create a class called `StudentServiceImpl`.

> Provided a \_ _createRecord_ \_ method to create student records.

![Screenshot](/infobook_backend/src/main/resources/static/images/student_service_impl.png)

Two helper methods mapToEntity and mapToDto to improve code readability and maintainability. These methods handle the conversion `StudentDto` to `Student` before saving it to the database, and then convert the saved `Student` back to `StudentDto` before returning it.

![Screenshot](/infobook_backend/src/main/resources/static/images/helper_mapping_methods.png)
