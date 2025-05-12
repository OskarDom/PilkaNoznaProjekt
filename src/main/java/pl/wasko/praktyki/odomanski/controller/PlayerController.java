package pl.wasko.praktyki.odomanski.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;
import pl.wasko.praktyki.odomanski.model.Player;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Dodany endpoint POST
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.ok(savedPlayer);
    }
}
