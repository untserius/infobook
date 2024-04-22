package com.serius.infobook.service.impl;

import com.serius.infobook.entity.User;
import com.serius.infobook.mapper.ObjectMapper;
import com.serius.infobook.payload.UserDto;
import com.serius.infobook.repository.UserRepository;
import com.serius.infobook.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto signup(UserDto userDto) {
        User user = ObjectMapper.mapUserToEntity(userDto);
        User savedUser = userRepository.save(user);
        UserDto dto = ObjectMapper.mapUserToDto(savedUser);
        return dto;
    }
}
