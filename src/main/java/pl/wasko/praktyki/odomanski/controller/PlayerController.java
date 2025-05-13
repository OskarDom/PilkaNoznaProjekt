package pl.wasko.praktyki.odomanski.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;
import pl.wasko.praktyki.odomanski.model.Player;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        logger.info("Pobrano wszystkich graczy");
        return playerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        logger.info("Dodawanie nowego gracza: {}", player.getName());
        Player savedPlayer = playerRepository.save(player);
        logger.info("Gracz zapisany z ID: {}", savedPlayer.getId());
        return ResponseEntity.ok(savedPlayer);
    }
}
