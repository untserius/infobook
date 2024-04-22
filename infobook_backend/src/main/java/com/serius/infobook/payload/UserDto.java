package com.serius.infobook.payload;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String userRole;
}
