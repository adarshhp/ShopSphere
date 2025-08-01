package com.example.signup.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // or load from config

	    public String generateToken(String email) {
	        return Jwts.builder()
	                .setSubject(email)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
	                .signWith(key)
	                .compact();
	    }

	    public String extractEmail(String token) {
	        return Jwts.parserBuilder().setSigningKey(key).build()
	                .parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            return false;
	        }
	    }
	}

