package pl.wasko.praktyki.odomanski.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import pl.wasko.praktyki.odomanski.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        if (username == null || username.isEmpty()) {
            logger.warn("Próba logowania bez podanej nazwy użytkownika");
            return ResponseEntity.badRequest().body("Brakuje nazwy użytkownika");
        }

        String token = JwtUtil.generateToken(username);
        logger.info("Wygenerowano token dla użytkownika: {}", username);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginInfo() {
        logger.info("Wywołano GET /auth/login (info endpoint)");
        return ResponseEntity.ok("Użyj POST z JSON-em {\"username\": \"o.domanski\"} aby uzyskać token.");
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        logger.info("Sprawdzanie poprawności tokena");
        String username = JwtUtil.validateToken(token);
        if (username != null) {
            logger.info("Token prawidłowy – użytkownik: {}", username);
            return ResponseEntity.ok("Token prawidłowy dla użytkownika: " + username);
        } else {
            logger.warn("Token nieprawidłowy");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token nieprawidłowy");
        }
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        logger.info("Dostęp do chronionego zasobu");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Brak lub niepoprawny nagłówek Authorization");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Brak lub niepoprawny nagłówek Authorization");
        }

        String token = authHeader.substring(7);
        String username = JwtUtil.validateToken(token);
        if (username == null) {
            logger.warn("Token nieprawidłowy przy dostępie do /protected");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token nieprawidłowy");
        }

        logger.info("Dostęp przyznany dla użytkownika: {}", username);
        return ResponseEntity.ok("Witaj, " + username + "! Masz dostęp do chronionego zasobu.");
    }
}
