package com.serius.infobook.controller;

import com.serius.infobook.payload.ListStudentDto;
import com.serius.infobook.payload.StudentDto;
import com.serius.infobook.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
// The @RequestMapping annotation at the class level specifies the base URL for all endpoints in this controller (/api/v1/students).
@RequestMapping("/api/v1/students")
public class StudentController {

    // Initialised Service interface to use the createRecord method to create a record.
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // The @PostMapping annotation at the method level specifies that the createStudent method will handle POST requests to the /addStudent endpoint under the base URL.
    @PostMapping("/addStudent")
    // @RequestBody accepts a StudentDto object in the request body. This object represents the data of the student to be created.
    // ResponseEntity method returns a ResponseEntity object, which allows you to customize the HTTP response.
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        StudentDto dto = studentService.createRecord(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getStudent/{id}") // You have to give the id in {id} field.
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){ // @PathVariable is used to accept or retrieve the given 'id' value in URL.
        StudentDto dto = studentService.getStudentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getALLStudent(){
        List<StudentDto> dtos = studentService.getAllStudent();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/updateStudent/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){ // You have to give a id and a body containing updated values.
        StudentDto dto = studentService.updateRecord(id, studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id){
        StudentDto dto = studentService.deleteStudentById(id);
        return new ResponseEntity<>("Successfully Deleted Id No: " + dto.getId(), HttpStatus.OK);
    }

    @GetMapping("/fetchallbypaginationandsorting")
    public ResponseEntity<ListStudentDto> fetchAllStudent(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        ListStudentDto listStudentDto = studentService.fetchAllStudentByPaginationAndSorting(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(listStudentDto, HttpStatus.OK);
    }

    @GetMapping("/fetchallbypagination")
    public ResponseEntity<ListStudentDto> fetchAllStudents(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
        ListStudentDto listStudentDto = studentService.getAllStudentByPagination(pageNo, pageSize);
        return new ResponseEntity<>(listStudentDto, HttpStatus.OK);
    }

}
