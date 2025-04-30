package pl.wasko.praktyki.odomanski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.praktyki.odomanski.model.Club;
import pl.wasko.praktyki.odomanski.model.Player;
import pl.wasko.praktyki.odomanski.model.Transaction;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;
import pl.wasko.praktyki.odomanski.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transakcje")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        Optional<Player> pilkarz = playerRepository.findById(transaction.getPilkarz().getId());
        Optional<Club> klub;
        klub = clubRepository.findById(transaction.getKlub().getId());

        if (pilkarz.isPresent() && klub.isPresent()) {
            transaction.setPilkarz(pilkarz.get());
            transaction.setKlub(klub.get());
            return ResponseEntity.ok(transactionRepository.save(transaction));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
