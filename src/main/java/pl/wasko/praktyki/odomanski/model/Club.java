package pl.wasko.praktyki.odomanski.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference(value = "club-transactions")  // Dopasowanie do Transaction
    private List<Transaction> transactions;

    public Club() {
    }

    public Club(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Club(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
