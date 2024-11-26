package com.employeeTracking.OAuth2Demo.config;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JWTConfig {

    private String secret = "";
    private long validityInMilliseconds = 3600000;

    
    @SuppressWarnings("deprecation")
    public String generateToken(String email, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setSubject(email)
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();

        return claims.get("roles", List.class);
    }

    @SuppressWarnings("deprecation")
    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }


    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
}
