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

    private LocalDate dataPrzejscia;
    private BigDecimal kwota;

    // Domyślny konstruktor
    public Transaction(String lionelMessi, String barcelona, String psg, double v) {
    }

    // Konstruktor z parametrami
    public Transaction(Player player, Club club, LocalDate dataPrzejscia, BigDecimal kwota) {
        this.player = player;
        this.club = club;
        this.dataPrzejscia = dataPrzejscia;
        this.kwota = kwota;
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

    public LocalDate getDataPrzejscia() {
        return dataPrzejscia;
    }

    public void setDataPrzejscia(LocalDate dataPrzejscia) {
        this.dataPrzejscia = dataPrzejscia;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }
}
