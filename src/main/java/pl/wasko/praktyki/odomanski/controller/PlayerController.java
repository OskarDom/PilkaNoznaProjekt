package pl.wasko.praktyki.odomanski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;

@Controller
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public String showPlayers(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "players"; // plik players.html w templates
    }
}
