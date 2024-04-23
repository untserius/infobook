package com.serius.infobook.service;

import com.serius.infobook.payload.LoginDto;
import com.serius.infobook.payload.UserDto;

public interface UserService {
    public UserDto signup(UserDto userDto);

    public void deleteUser(long id);

    public String signin(LoginDto loginDto);
}
