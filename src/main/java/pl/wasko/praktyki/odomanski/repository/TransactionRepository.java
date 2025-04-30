package pl.wasko.praktyki.odomanski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.praktyki.odomanski.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
