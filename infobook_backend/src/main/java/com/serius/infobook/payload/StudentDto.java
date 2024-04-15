package com.serius.infobook.payload;

import com.serius.infobook.validator.OnlyGmail;
import jakarta.validation.constraints.*;

public class StudentDto {
    private Long id;
    private String name;

    @NotEmpty
    @Size(min = 10, message = "Please enter a valid mobile no.")
    private String mobile;

    @NotEmpty
    @Email(message = "Enter a valid email id")
    @OnlyGmail
    private String email;
    private String address;
    private String university;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
