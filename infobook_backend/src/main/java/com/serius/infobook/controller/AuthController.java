package com.serius.infobook.controller;

import com.serius.infobook.entity.User;
import com.serius.infobook.payload.UserDto;
import com.serius.infobook.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto){
        UserDto signup = userService.signup(userDto);
        return new ResponseEntity<>(signup, HttpStatus.CREATED);
    }
}
