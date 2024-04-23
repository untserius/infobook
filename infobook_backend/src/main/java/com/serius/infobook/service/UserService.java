package com.serius.infobook.service;

import com.serius.infobook.entity.User;
import com.serius.infobook.payload.LoginDto;
import com.serius.infobook.payload.UserDto;

import java.util.Optional;

public interface UserService {
    public UserDto signup(UserDto userDto);

    public void deleteUser(long id);

    public String signin(LoginDto loginDto);

    public Optional<User> findByUsername(String username);
}
