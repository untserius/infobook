package com.serius.infobook.service.impl;

import com.serius.infobook.entity.User;
import com.serius.infobook.mapper.ObjectMapper;
import com.serius.infobook.payload.LoginDto;
import com.serius.infobook.payload.UserDto;
import com.serius.infobook.repository.UserRepository;
import com.serius.infobook.service.JwtService;
import com.serius.infobook.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public UserDto signup(UserDto userDto) {
        User user = ObjectMapper.mapUserToEntity(userDto);
        User savedUser = userRepository.save(user);
        UserDto dto = ObjectMapper.mapUserToDto(savedUser);
        return dto;
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }

    @Override
    public String signin(LoginDto loginDto) {
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            User user = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                return jwtService.generateToken(user);
            }
        }
        return null;
    }
}
