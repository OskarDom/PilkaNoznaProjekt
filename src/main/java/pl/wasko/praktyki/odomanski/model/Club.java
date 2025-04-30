package pl.wasko.praktyki.odomanski.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String kraj;

    @OneToMany(mappedBy = "klub", cascade = CascadeType.ALL)
    private List<Transaction> transakcje;

    // Domyślny konstruktor wymagany przez JPA
    public Club() {
    }

    // Konstruktor z parametrami
    public Club(String nazwa, String kraj) {
        this.nazwa = nazwa;
        this.kraj = kraj;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public List<Transaction> getTransakcje() {
        return transakcje;
    }

    public void setTransakcje(List<Transaction> transakcje) {
        this.transakcje = transakcje;
    }

    public void getClass(String kraj) {
    }
}
