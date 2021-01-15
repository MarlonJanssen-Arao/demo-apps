package tech.arao.minibank.data.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 5702854716359367218L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Transaction> transactions = new ArrayList<>();


    protected User() {
        super();
    }

    public User(String name) {
        super();
        this.setName(name);
    }

    public User(Long id, String name) {
        super();
        this.setId(id);
        this.setName(name);
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setUser(null);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setUser(this);
    }

    public void removeTransaction(Transaction transaction) {
        accounts.remove(transaction);
        transaction.setUser(null);
    }


    @Override
    public String toString() {
        return "User { " + "id=" + id +
                       ", name='" + name + '\'' +
                   ", accounts=" + accounts.toString() +
               ", transactions=" + transactions.toString() +  " }";
    }
}
