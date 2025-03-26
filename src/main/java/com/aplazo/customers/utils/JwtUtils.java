package com.aplazo.customers.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.aplazo.customers.constants.Properties;
import com.aplazo.customers.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Component
public class JwtUtils {

    private final Properties properties;

    public String buildToken(User user, long expiration) {
        return Jwts.builder()
            
            .claim("username", user.getUsername())
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey())
            .compact();

    }

    public SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(this.properties.getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getSubject(String token ) {
        Claims claims = Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, User user) {
        String username = getSubject(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return claims.getExpiration();
    }

}
