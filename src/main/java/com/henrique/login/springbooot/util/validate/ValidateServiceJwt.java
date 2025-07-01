package com.henrique.login.springbooot.util.validate;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class ValidateServiceJwt {

    private static final String SECRET_KEY="supersecretkeyodfnsaidfndinfasdfbdasijfnsdia143278648732@8473y412nbdsuaf";
    private static final long EXPIRATION_TIME= 3600000;
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("Token inválido: " + e.getMessage());
        }

        return false;
    }
}
