package com.serius.infobook.controller;

import com.auth0.jwt.JWT;
import com.serius.infobook.entity.User;
import com.serius.infobook.payload.ClientResponse;
import com.serius.infobook.payload.LoginDto;
import com.serius.infobook.payload.UserDto;
import com.serius.infobook.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginDto loginDto){
        String signedToken = userService.signin(loginDto);
        if(signedToken != null){
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setType("Bearer");
            clientResponse.setToken(signedToken);
            clientResponse.setExpiretime(JWT.decode(signedToken).getExpiresAt().getTime() / 1000);
        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
    }

}
