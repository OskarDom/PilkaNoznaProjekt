package pl.wasko.praktyki.odomanski.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pilkarz_id")
    @JsonBackReference(value = "player-transactions")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "klub_id")
    @JsonBackReference(value = "club-transactions")
    private Club club;

    private LocalDate transactionDate;
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(Player player, Club club, LocalDate dataPrzejscia, BigDecimal kwota) {
        this.player = player;
        this.club = club;
        this.transactionDate = dataPrzejscia;
        this.amount = kwota;
    }

    public Long getId() {
        return id;
    }

    public Player getPilkarz() {
        return player;
    }

    public void setPilkarz(Player player) {
        this.player = player;
    }

    public Club getKlub() {
        return club;
    }

    public void setKlub(Club club) {
        this.club = club;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Player getPlayer() {
        return null;
    }

    public Logger getNewClub() {
        return null;
    }
}
