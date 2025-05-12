package pl.wasko.praktyki.odomanski.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "club_id")
    @JsonBackReference
    private Club club;

    @OneToMany(mappedBy = "player")
    @JsonManagedReference(value = "player-transactions")
    private List<Transaction> transactions;

    public Player() {}

    public Player(String name, String surname, Integer age, Club club) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.club = club;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
