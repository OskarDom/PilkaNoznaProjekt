package pl.wasko.praktyki.odomanski.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // Domyślny konstruktor wymagany przez JPA
    public Club() {
    }

    // Konstruktor z parametrami
    public Club(String nazwa, String kraj) {
        this.name = nazwa;
        this.country = kraj;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void getClass(String country) {
    }
}
