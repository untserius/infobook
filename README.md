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

> [!NOTE]
> So, why use DTOs?

> DTOs help reduce the amount of data transferred over the network or between different layers of your application. Since they contain only the necessary information.

> DTOs encapsulate the data being transferred, providing a layer of security by hiding sensitive or unnecessary information that should not be exposed outside.

> By using DTOs, you can decouple different parts of your application. This makes it easier to modify or extend the data structure without affecting other parts of the application. For example, if you decide to add or remove fields from the Student object, you can update the corresponding DTOs without impacting the entire system.

### 6. Service

Create a **service** package, inside it create a class called `StudentService`.

> Create

![Screenshot](/infobook_backend/src/main/resources/static/images/student_service.png)

> Retrieve

![Screenshot](/infobook_backend/src/main/resources/static/images/get_student_by_id.png)

![Screenshot](/infobook_backend/src/main/resources/static/images/all_student_service.png)

> Update

![Screenshot](/infobook_backend/src/main/resources/static/images/update_record.png)

> Delete

![Screenshot](/infobook_backend/src/main/resources/static/images/delete_student_service.png)

#### 6.1 Service Implementations

Create a **impl** package inside the **service** package and create a class called `StudentServiceImpl`.

> Provided a \_ _createRecord_ \_ method to create student records.

![Screenshot](/infobook_backend/src/main/resources/static/images/student_service_impl.png)

> [!NOTE]
> Why extra two methods ?

> Two helper methods mapToEntity() and mapToDto() to improve code readability and maintainability. These methods handle the conversion `StudentDto` to `Student` before saving it to the database, and then convert the saved `Student` back to `StudentDto` before returning it.

![Screenshot](/infobook_backend/src/main/resources/static/images/helper_mapping_methods.png)

> Provided a \_ _getStudentById_ \_ method to find the student for the given id no.

![Screenshot](/infobook_backend/src/main/resources/static/images/get_student_by_id_impl.png)

> Provided a \_ _getAllStudent_ \_ method to find the student for the given id no.

![Screenshot](/infobook_backend/src/main/resources/static/images/get_all_student_impl.png)

> Provided a \_ _updateRecord_ \_ method to find the student for the given id no.

![Screenshot](/infobook_backend/src/main/resources/static/images/update_record_impl.png)

> [!WARNING]
> Why mapToEntity() method won't work here ?

> Because the student object you're mapping from doesn't have an ID yet. You should leave the ID unset, as it will be determined by the database when the entity is saved.

> Provided a \_ _deleteStudentById_ \_ method to delete student for the given id no.

![Screenshot](/infobook_backend/src/main/resources/static/images/delete_student_impl.png)

### 6. Controller

Create a **controller** package, inside it create a class called `StudentController`.

![Screenshot](/infobook_backend/src/main/resources/static/images/student_controller.png)

![Screenshot](/infobook_backend/src/main/resources/static/images/get_student_by_id_controller.png)

![Screenshot](/infobook_backend/src/main/resources/static/images/update_student_controller.png)

![Screenshot](/infobook_backend/src/main/resources/static/images/delete_student_controller.png)

### 7. API Documentation

You can refer to API Documentation for testing purposes [Infobook API's](https://documenter.getpostman.com/view/32566359/2sA3Bhfaka).

### 7. Issues

- [] https://github.com/untserius/infobook/issues/1
