package pl.wasko.praktyki.odomanski.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wasko.praktyki.odomanski.model.Transaction;
import pl.wasko.praktyki.odomanski.repository.TransactionRepository;

import java.util.List;

@Component
public class TransactionsScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TransactionsScheduler.class);
    private final TransactionRepository transactionRepository;

    public TransactionsScheduler(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Co minutę wypisuje transakcje
    @Scheduled(cron = "0 * * * * *") // raz na minutę
    public void logTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            logger.info("Brak transakcji w systemie.");
        } else {
            logger.info("Lista transakcji:");
            transactions.forEach(t -> logger.info("Transakcja: {}, zawodnik: {}, klub: {}, kwota: {}",
                    t.getId(),
                    t.getPlayer().getFirstName() + " " + t.getPlayer().getLastName(),
                    t.getNewClub().getName(),
                    t.getAmount()));
        }
    }
}
