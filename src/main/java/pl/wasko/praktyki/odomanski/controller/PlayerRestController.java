package pl.wasko.praktyki.odomanski.controller;

import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.model.Player;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerRepository playerRepository;

    public PlayerRestController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
