package pl.wasko.praktyki.odomanski.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

public class JwtAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String SECRET_KEY = "twoj_sekret_klucz_musi_miec_32_znaki_lub_wiecej";
    private static final long EXPIRATION_TIME = 86400000; // 1 dzień
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generuje token JWT dla danego użytkownika
     */
    public static String generateToken(String username) {
        logger.info("Generowanie tokenu JWT dla użytkownika: {}", username);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Waliduje token JWT i zwraca nazwę użytkownika, jeśli token jest poprawny
     */
    public static String validateToken(String token) {
        try {
            String username = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            logger.info("Token JWT prawidłowy dla użytkownika: {}", username);
            return username;
        } catch (JwtException e) {
            // Token jest nieprawidłowy lub wygasł
            logger.warn("Token JWT nieprawidłowy lub wygasł: {}", e.getMessage());
            return null;
        }
    }
}
