package com.serius.infobook.exception;

import com.serius.infobook.payload.DatabaseErrorDetails;
import com.serius.infobook.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.sql.SQLIntegrityConstraintViolationException;

//  This annotation marks the class as a global exception handler.
@ControllerAdvice
/* ResponseEntityExceptionHandler --> Contains built-in exception handling logic for MVC controllers.
                                   By extending this class, you can override its methods to provide custom exception handling. */
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler --> annotation marks methods that handle specific exceptions.
    // To handle SQL Violation exceptions
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<DatabaseErrorDetails> handleSqlExceptions(SQLIntegrityConstraintViolationException e) {
        DatabaseErrorDetails sqlError = new DatabaseErrorDetails(new Date(), e.getMessage(), e.getErrorCode(), e.getCause());
        return new ResponseEntity<>(sqlError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // My custom exception handler that will show the message(String) captured by ResourceNotFound, which I have passed in the business logic in StudentServiceImpl
    @ExceptionHandler(ResourceNotFound.class)
    /* WebRequest --> represents the current web request being handled by your Spring MVC application.
                      It provides access to various attributes and metadata associated with the request.*/
    public ResponseEntity<ErrorDetails> handleCustomExceptions(ResourceNotFound e, WebRequest webRequest) {
        // .getDescription() --> provide information about the specific web request, including the URI being accessed and the client IP address.
        ErrorDetails customError = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // To handle NullPointerExceptions
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

/* How to directly handle exceptions from controller?

See Example:-

    @PostMapping("/updateStudent/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){ // You have to give a id and a body containing updated values.
        Try {
            StudentDto dto = studentService.updateRecord(id, studentDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (ResourceNotFound e) {
            return new ResponseEntity<>("No student found with id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
*/

/**
 * Exceptions Testcases that I can make:
 * <p>
 * 1. SQLIntegrityConstraintViolationException --> (createRecord) incomplete body, it will throw an exception
 * 2. ResourceNotFound(Custom Exception) --> NoSuchValuePresent --> (getStudentById) If Id is not found.
 * 3. NullPointerException --> (updateRecord) If id not found
 * 4. NullPointerException --> (deleteStudentById) If id not found
 */