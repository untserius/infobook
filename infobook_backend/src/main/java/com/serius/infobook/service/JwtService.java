package com.serius.infobook.service;

import com.serius.infobook.entity.User;

public interface JwtService {

    public String generateToken (User user);

    public String fetchUsername(String token);
}
