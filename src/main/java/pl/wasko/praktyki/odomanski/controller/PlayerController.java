package pl.wasko.praktyki.odomanski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.model.Player;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;

import java.util.List;

@RestController
@RequestMapping("/pilkarze")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id) {
        return playerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Player create(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody Player playerDetails) {
        return playerRepository.findById(id)
                .map(p -> {
                    p.setName((String) playerDetails.getName());
                    p.setSurname(playerDetails.getSurname());
                    p.setAge(playerDetails.getAge());
                    return ResponseEntity.ok(playerRepository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return playerRepository.findById(id)
                .map(p -> {
                    playerRepository.delete(p);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
