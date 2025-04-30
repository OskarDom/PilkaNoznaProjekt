package pl.wasko.praktyki.odomanski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.model.Club;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;


import java.util.List;

@RestController
@RequestMapping("/kluby")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable Long id) {
        return clubRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Club create(@RequestBody Club club) {
        return clubRepository.save(club);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> update(@PathVariable Long id, @RequestBody Club clubDetails) {
        return clubRepository.findById(id)
                .map(k -> {
                    k.setNazwa(clubDetails.getNazwa());
                    k.getClass(clubDetails.getKraj());
                    return ResponseEntity.ok(clubRepository.save(k));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clubRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
