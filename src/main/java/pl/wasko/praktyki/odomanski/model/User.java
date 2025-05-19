package pl.wasko.praktyki.odomanski.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;  // np. "ADMIN", "USER"
    private boolean isDeleted = false;  // Flaga usunięcia użytkownika

    public User(String username, String hashedPassword, String role, boolean b) {
    }

    public void setUsername(String newUsername) {
    }

    public void setPassword(String encode) {
    }

    public void setRole(String newRole) {
    }

    public void setIsDeleted(boolean b) {
    }

    public String getUsername() {
        return "";
    }

    public String getPassword() {
        return "";
    }

    public String getRole() {
        return "";
    }

    public void setDeleted(boolean b) {
    }

    // Gettery i settery
}
