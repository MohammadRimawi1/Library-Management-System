package com.exalt.library.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * a class responsible for generating and validating JWTs
 * @author Mohammad Rimawi
 */
@Service
public class JwtService {

    @Value("${library.jwt.secret}")
    private String secret;

    @Value("${library.jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * generates a signed JWT for the given email, embedding their role as a claim
     * @param email
     * @param role
     * @return
     */
    public String generateToken(String email, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * extracts the email (subject) from a token - throws if the token is invalid/expired
     * @param token
     * @return
     */
    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * extracts the role claim from a token
     * @param token
     * @return
     */
    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    /**
     * checks whether a token is valid (correctly signed and not expired)
     * @param token
     * @return
     */
    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Parses and validates a JWT token to extract its claims
     * @param token
     * @return
     */
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}