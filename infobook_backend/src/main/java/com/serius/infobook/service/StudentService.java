package com.serius.infobook.service;


import com.serius.infobook.payload.StudentDto;

public interface StudentService {

    // 'createRecord' method which takes a StudentDto object as input and returns a StudentDto object.
    public StudentDto createRecord(StudentDto studentDto);
}
