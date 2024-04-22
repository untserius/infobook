package com.serius.infobook.mapper;

import com.serius.infobook.entity.Student;
import com.serius.infobook.entity.User;
import com.serius.infobook.payload.StudentDto;
import com.serius.infobook.payload.UserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class ObjectMapper {

    public static Student mapStudentToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());
        student.setEmail(studentDto.getEmail());
        student.setAddress(studentDto.getAddress());
        student.setUniversity(studentDto.getUniversity());
        return student;
    }

    public static StudentDto mapStudentToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setMobile(student.getMobile());
        studentDto.setEmail(student.getEmail());
        studentDto.setAddress(student.getAddress());
        studentDto.setUniversity(studentDto.getUniversity());
        return studentDto;
    }

    public static void updateStudentEntityFromDto(Student student, StudentDto studentDto) {

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

    public static User mapUserToEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());

        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));

        user.setPassword(hashedPassword);
        user.setUserRole(userDto.getUserRole());
        return user;
    }

    public static UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserRole(user.getUserRole());
        return userDto;
    }
}
