package pl.wasko.praktyki.odomanski.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import pl.wasko.praktyki.odomanski.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Brakuje nazwy użytkownika");
        }
        String token = JwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginInfo() {
        return ResponseEntity.ok("Użyj POST z JSON-em {\"username\": \"o.domanski\"} aby uzyskać token.");
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        String username = JwtUtil.validateToken(token);
        return username != null
                ? ResponseEntity.ok("Token prawidłowy dla użytkownika: " + username)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token nieprawidłowy");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Brak lub niepoprawny nagłówek Authorization");
        }

        String token = authHeader.substring(7);
        String username = JwtUtil.validateToken(token);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token nieprawidłowy");
        }

        return ResponseEntity.ok("Witaj, " + username + "! Masz dostęp do chronionego zasobu.");
    }
}
