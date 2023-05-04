/*
 * Copyright (C) 2023
 *
 * Author : z.soffi
 * File : JwtService.java
 */

package com.zayzou.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private final String SECRET_KEY = "77397A24432646294A404E635266556A576E5A7234753778214125442A472D4B";

    public String extractUsername(String jwt) {
        return "";
    }

    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(jwt)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
