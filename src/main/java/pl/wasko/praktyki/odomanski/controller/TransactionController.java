package pl.wasko.praktyki.odomanski.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.model.Transaction;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;
import pl.wasko.praktyki.odomanski.repository.TransactionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public List<Transaction> getAll() {
        logger.info("Pobieranie wszystkich transakcji");
        return transactionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        Long playerId = transaction.getPilkarz().getId();
        Long clubId = transaction.getKlub().getId();

        logger.info("Próba utworzenia transakcji: playerId={}, clubId={}", playerId, clubId);

        return playerRepository.findById(playerId)
                .flatMap(pilkarz -> clubRepository.findById(clubId)
                        .map(klub -> {
                            transaction.setPilkarz(pilkarz);
                            transaction.setKlub(klub);
                            Transaction saved = transactionRepository.save(transaction);
                            logger.info("Utworzono transakcję o ID: {}", saved.getId());
                            return ResponseEntity.ok(saved);
                        })
                ).orElseGet(() -> {
                    logger.warn("Nie udało się utworzyć transakcji – nie znaleziono gracza lub klubu (playerId={}, clubId={})", playerId, clubId);
                    return ResponseEntity.badRequest().build();
                });
    }
}
