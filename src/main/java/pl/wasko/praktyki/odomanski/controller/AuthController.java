package pl.wasko.praktyki.odomanski.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import pl.wasko.praktyki.odomanski.security.JwtAuthenticationFilter;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String token = JwtAuthenticationFilter.generateToken(username);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        String username = JwtAuthenticationFilter.validateToken(token);
        return username != null
                ? ResponseEntity.ok("Token prawidłowy dla użytkownika: " + username)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token nieprawidłowy");
    }
}
