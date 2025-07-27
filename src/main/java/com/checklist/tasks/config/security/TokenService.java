package com.checklist.tasks.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.checklist.tasks.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer("tasks")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(getAlgorithm());

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error ao se autenticar");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.from(ZoneOffset.UTC));
    }

    public String validateToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer("tasks")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
