package pl.wasko.praktyki.odomanski.controller;

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
        return playerRepository.findById(transaction.getPilkarz().getId())
                .flatMap(pilkarz ->
                        clubRepository.findById(transaction.getKlub().getId())
                                .map(klub -> {
                                    transaction.setPilkarz(pilkarz);
                                    transaction.setKlub(klub);
                                    return ResponseEntity.ok(transactionRepository.save(transaction));
                                })
                ).orElse(ResponseEntity.badRequest().build());
    }
}
