package com.serius.infobook.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.serius.infobook.entity.User;
import com.serius.infobook.service.JwtService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;

    private final static String USER_NAME ="dummyvalue";

    @PostConstruct
    public void setAlgorithm(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    @Override
    public String generateToken(User user) {
        String token = JWT.create()
                .withClaim(USER_NAME, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
        return token;
    }

    @Override
    public String fetchUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        String decodedUsername = decodedJWT.getClaim(USER_NAME).asString();
        return decodedUsername;
    }
}
