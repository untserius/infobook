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

Create a 'entity' package, inside it create a class called 'Student'.

![Screenshot of entity class.](infobook_backend\src\main\resources\static\images\entity.png)
