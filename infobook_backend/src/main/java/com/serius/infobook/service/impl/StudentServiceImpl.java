package com.serius.infobook.service.impl;

import com.serius.infobook.entity.Student;
import com.serius.infobook.payload.ListStudentDto;
import com.serius.infobook.payload.StudentDto;
import com.serius.infobook.repository.StudentRepository;
import com.serius.infobook.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // to mark the class as a Spring service component.
public class StudentServiceImpl implements StudentService {
    /* marking it as 'final' - ensures that once the dependency is set during object construction,
    it cannot be changed afterward. */
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create
    @Override
    public StudentDto createRecord(StudentDto studentDto){
        Student student = mapToEntity(studentDto); // conversion `StudentDto` to `Student` before saving it to the DB.
        Student savedStudent = studentRepository.save(student); // saved in DB
        StudentDto dto = mapToDto(savedStudent); // then convert the saved `Student` back to `StudentDto` before returning it.
        return dto;
    }

    // Retrieve
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(); //findById() - It will look for a student whose id no. is {id}.If not found, throw an error.
        StudentDto dto = mapToDto(student); // then convert the fetched `Student` back to `StudentDto` before returning it.
        return dto;
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> student = studentRepository.findAll(); // findAll() - It will look for all saved students in DB.
        /* The retrieved list of Student entities is converted into a stream using the stream() method.
        Then, the map method is used to transform each Student entity into a StudentDto object using the mapToDto method,
        and the toList terminal operation collects the transformed StudentDto objects into a list.
         */
        List<StudentDto> studentDtos = student.stream().map(o -> mapToDto(o)).toList();
        return studentDtos;
    }

    @Override
    public ListStudentDto getAllStudentByPagination(int pageNo, int pageSize) {
        /*
        Pageable is an interface in the Spring Framework used for pagination in data retrieval operations.

        The PageRequest.of() method typically takes two parameters:

        pageNo: The page number you want to retrieve.
        pageSize: The number of items (or records) you want to include on each page.
        */
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        /*
         Page<Student> can be thought of
         as storing a list of Student objects with additional metadata related to pagination.
         In the proper sense,
         a Page object in Spring Data contains a list of entities representing the current page of results.
         */
        Page<Student> all = studentRepository.findAll(pageable);

        // This list can be accessed via the getContent() method, which returns a List<Student> containing the entities on the current page.
        List<Student> content = all.getContent();
        List<StudentDto> dtoList = content.stream().map(e -> mapToDto(e)).toList();

        ListStudentDto listStudentDto = new ListStudentDto();

        // Setting of metadata
        listStudentDto.setStudentDto(dtoList); //  sets the list of StudentDto objects (dtoList) in the ListStudentDto object.
        listStudentDto.setTotalPages(all.getTotalPages()); // The total number of pages in the paginated result.
        listStudentDto.setTotalElements((int) all.getTotalElements()); // The total number of elements across all pages.
        listStudentDto. setLastPage(all.isLast()); // Whether the current page is the last page.
        listStudentDto.setFirstPage(all.isFirst()); // Whether the current page is the first page.
        listStudentDto.setPageNumber(all.getNumber()); // The current page number.
        return listStudentDto;
    }

    @Override
    public ListStudentDto fetchAllStudentByPaginationAndSorting(int pageNo, int pageSize, String sortBy, String sortDir) {
        //The sort parameter is constructed based on the sorting direction (sortDir) and sorting field (sortBy).
        // If the sorting direction is ascending (ASC), it creates an ascending sort;
        // otherwise, it creates a descending sort.
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> all = studentRepository.findAll(pageable);
        List<Student> content = all.getContent();
        List<StudentDto> dtoList = content.stream().map(e -> mapToDto(e)).toList();

        ListStudentDto listStudentDto = new ListStudentDto();

        listStudentDto.setStudentDto(dtoList);
        listStudentDto.setTotalPages(all.getTotalPages());
        listStudentDto.setTotalElements((int) all.getTotalElements());
        listStudentDto.setFirstPage(all.isFirst());
        listStudentDto.setLastPage(all.isLast());
        listStudentDto.setPageNumber(all.getNumber());

        return listStudentDto;
    }

    // Update
    @Override
    public StudentDto updateRecord(Long id, StudentDto studentDto) {
        /*
         findById() - returns an Optional containing the entity with the specified ID if it exists in the database,
         or an empty Optional if it does not exist.
        */
        Optional<Student> byId = studentRepository.findById(id);
        // checks if the Optional contains a non-null value.
        if(byId.isPresent()){
            // retrieves the value from the Optional, which is the Student entity if it exists.
            Student existingStudent = byId.get();

            // Update the existing student entity with the data from the DTO
            updateEntityFromDto(existingStudent, studentDto);

            Student savedStudent = studentRepository.save(existingStudent);

            StudentDto dto = mapToDto(savedStudent);
            return dto;
        }
        // If the student with the specified ID does not exist in the database.
        return null;
    }

    @Override
    public StudentDto deleteStudentById(Long id) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            // If the student exists, delete it using the deleteById method.
            studentRepository.deleteById(id);
            // Convert the deleted Student entity to a StudentDto object
            Student deletedStudent = byId.get();
            StudentDto dto = mapToDto(deletedStudent);
            return dto;
        }
        return null;
    }


    // Helper method to convert StudentDto to Student entity
    private Student mapToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(student.getId());
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setUniversity(studentDto.getUniversity());
        return student;
    }

    // Helper method to convert Student entity to StudentDto
    private StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setMobile(student.getMobile());
        dto.setEmail(student.getEmail());
        dto.setAddress(student.getAddress());
        dto.setUniversity(student.getUniversity());
        return dto;
    }

    // Helper method to update only the non-null fields from the DTO
    private void updateEntityFromDto(Student student, StudentDto studentDto) {

        if (studentDto.getName() != null) {
            student.setName(studentDto.getName());
        }
        if (studentDto.getMobile() != null) {
            student.setMobile(studentDto.getMobile());
        }
        if (studentDto.getEmail() != null) {
            student.setEmail(studentDto.getEmail());
        }
        if (studentDto.getAddress() != null) {
            student.setAddress(studentDto.getAddress());
        }
        if (studentDto.getUniversity() != null) {
            student.setUniversity(studentDto.getUniversity());
        }
    }
}
