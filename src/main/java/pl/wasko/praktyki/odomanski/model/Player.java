package pl.wasko.praktyki.odomanski.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;
    private Integer wiek;

    @ManyToOne
    @JoinColumn(name = "klub_id")
    private Club club;

    @OneToMany(mappedBy = "pilkarz")
    private List<Transaction> transakcje;

    // Konstruktor
    public Player(String imie, String nazwisko, int wiek, Club club) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.club = club;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public Club getKlub() {
        return club;
    }

    public void setKlub(Club club) {
        this.club = club;
    }

    public List<Transaction> getTransakcje() {
        return transakcje;
    }

    public void setTransakcje(List<Transaction> transakcje) {
        this.transakcje = transakcje;
    }
}
