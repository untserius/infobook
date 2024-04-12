package com.serius.infobook.service.impl;

import com.serius.infobook.entity.Student;
import com.serius.infobook.payload.StudentDto;
import com.serius.infobook.repository.StudentRepository;

public class StudentServiceImpl {
    private StudentRepository studentRepository;


    public StudentDto createRecord(StudentDto studentDto){
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setUniversity(studentDto.getUniversity());

        Student savedStudent = studentRepository.save(student);

        StudentDto dto = new StudentDto();
        dto.setName(savedStudent.getName());
        dto.setMobile(savedStudent.getMobile());
        dto.setEmail(savedStudent.getEmail());
        dto.setAddress(savedStudent.getAddress());
        dto.setUniversity(savedStudent.getUniversity());

        return dto;
    };
}
