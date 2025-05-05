package pl.wasko.praktyki.odomanski.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pilkarz_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "klub_id")
    private Club club;

    private LocalDate transactionDate;
    private BigDecimal amount;

    // Konstruktor z parametrami
    public Transaction(Player player, Club club, LocalDate dataPrzejscia, BigDecimal kwota) {
        this.player = player;
        this.club = club;
        this.transactionDate = dataPrzejscia;
        this.amount = kwota;
    }

    // Gettery i settery
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
}
