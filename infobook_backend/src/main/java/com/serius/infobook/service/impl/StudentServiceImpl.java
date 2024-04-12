package com.serius.infobook.service.impl;

import com.serius.infobook.entity.Student;
import com.serius.infobook.payload.StudentDto;
import com.serius.infobook.repository.StudentRepository;
import com.serius.infobook.service.StudentService;
import org.springframework.stereotype.Service;

@Service // to mark the class as a Spring service component.
public class StudentServiceImpl implements StudentService {
    /* marking it as 'final' - ensures that once the dependency is set during object construction,
    it cannot be changed afterwards. */
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // CREATE
    @Override
    public StudentDto createRecord(StudentDto studentDto){
        Student student = mapToEntity(studentDto);

        Student savedStudent = studentRepository.save(student);

        StudentDto dto = mapToDto(savedStudent);

        return dto;
    }

    // Helper method to convert StudentDto to Student entity
    private Student mapToEntity(StudentDto studentDto) {
        Student student = new Student();
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
        dto.setName(student.getName());
        dto.setMobile(student.getMobile());
        dto.setEmail(student.getEmail());
        dto.setAddress(student.getAddress());
        dto.setUniversity(student.getUniversity());
        return dto;
    }
}
