package com.aidaml.cc.demo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

public class JwtTokenProvider {
    
    private static final SecretKey key = Jwts.SIG.HS256.key().build();

    public static String generateToken(String username) {

        Date now = Date.from(Instant.now());
        
        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(new Date(now.getTime() + 3600000)) // 1 hour
            .signWith(key)
            .compact();
    }

    public static Claims validateToken(String token) throws JwtException {

        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

}