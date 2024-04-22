package com.serius.infobook.repository;

import com.serius.infobook.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*'JpaRepository' - interface by Spring Data JPA which provides CRUD (Create, Read, Update, Delete) operations
 for the specified entity type.

 'Student' - entity type.
 'Long' - type of entity's primary key.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
