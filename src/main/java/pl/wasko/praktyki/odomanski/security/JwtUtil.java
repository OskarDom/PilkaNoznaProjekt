package pl.wasko.praktyki.odomanski.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Klucz powinien mieć minimum 256 bitów (32 znaki dla HS256)
    private static final String SECRET_KEY = "twoj_sekret_klucz_musi_miec_32_znaki_lub_wiecej";

    //Czas wygaśnięcia tokenu (1 dzień w milisekundach)
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    //Tworzenie klucza na podstawie ciągu znaków
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generuje JWT dla danego użytkownika
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Waliduje token i zwraca nazwę użytkownika (subject), jeśli token jest poprawny
     */
    public static String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            // Token nieprawidłowy lub wygasł
            return null;
        }
    }
}
