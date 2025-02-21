package it.immobiliare365.security.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTools {

    @Value("${spring.jwt.secret}") String secret;


    public String createToken(String email) {
        long accessTokenValidity = 15 * 60 * 1000;
        return Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String generateRefreshToken(String email) {
        long refreshTokenValidity = 24 * 60 * 60 * 1000;
        return Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public String decodeToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
