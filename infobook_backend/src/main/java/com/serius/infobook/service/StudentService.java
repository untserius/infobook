package com.serius.infobook.service;


import com.serius.infobook.payload.ListStudentDto;
import com.serius.infobook.payload.StudentDto;

import java.util.List;

public interface StudentService {

    // 'createRecord' method which takes a StudentDto object as input and returns a StudentDto object.
    public StudentDto createRecord(StudentDto studentDto);

    // 'getStudentById' method which takes a Long object as input and returns a StudentDto object.
    public StudentDto getStudentById(Long id);

    // 'getAllStudent' method which fetches all Students data as List.
    public List<StudentDto> getAllStudent();

    // 'getAllStudentByPagination' method which fetches all Students data as List. (pagination)
    public ListStudentDto getAllStudentByPagination(int pageNo, int pageSize);

    // 'fetchAllStudentByPaginationAndSorting' method which fetches all Students data as List. (pagination and sorting)
    public ListStudentDto fetchAllStudentByPaginationAndSorting(int pageNo, int pageSize, String sortBy, String sortDir);

    // 'updateRecord' method takes a id and a StudentDto object as input, using that it returns an updated StudentDto object.
    public StudentDto updateRecord(Long id, StudentDto studentDto);

    public StudentDto deleteStudentById(Long id);
}
