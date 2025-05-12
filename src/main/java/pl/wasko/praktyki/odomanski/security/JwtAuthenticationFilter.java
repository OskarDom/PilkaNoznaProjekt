package pl.wasko.praktyki.odomanski.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;


public class JwtAuthenticationFilter {
    private static final String SECRET_KEY = "twoj_sekret_klucz_musi_miec_32_znaki_lub_wiecej";
    private static final long EXPIRATION_TIME = 86400000; // 1 dzień

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null; // Token nieprawidłowy
        }
    }



}


