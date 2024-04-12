package com.serius.infobook.controller;

import com.serius.infobook.entity.Student;
import com.serius.infobook.payload.StudentDto;
import com.serius.infobook.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto){
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

}
