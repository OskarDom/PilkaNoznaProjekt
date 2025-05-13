package pl.wasko.praktyki.odomanski.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.model.Club;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private static final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public List<Club> getAll() {
        logger.info("Pobieranie wszystkich klubów");
        return clubRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable Long id) {
        logger.info("Pobieranie klubu o ID: {}", id);
        return clubRepository.findById(id)
                .map(club -> {
                    logger.info("Znaleziono klub: {}", club.getName());
                    return ResponseEntity.ok(club);
                })
                .orElseGet(() -> {
                    logger.warn("Nie znaleziono klubu o ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public Club create(@RequestBody Club club) {
        Club savedClub = clubRepository.save(club);
        logger.info("Utworzono nowy klub: {} (ID: {})", savedClub.getName(), savedClub.getId());
        return savedClub;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> update(@PathVariable Long id, @RequestBody Club clubDetails) {
        logger.info("Aktualizacja klubu o ID: {}", id);
        return clubRepository.findById(id)
                .map(c -> {
                    c.setName(clubDetails.getName());
                    c.setCountry(clubDetails.getCountry());
                    Club updatedClub = clubRepository.save(c);
                    logger.info("Zaktualizowano klub: {}", updatedClub.getName());
                    return ResponseEntity.ok(updatedClub);
                })
                .orElseGet(() -> {
                    logger.warn("Nie znaleziono klubu do aktualizacji o ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Usuwanie klubu o ID: {}", id);
        clubRepository.deleteById(id);
        logger.info("Usunięto klub o ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
