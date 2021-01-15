package tech.arao.minibank.data.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "Account")
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 7259809408602606173L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false)
    private BigDecimal balance;


    protected Account() {
        super();
    }

    public Account(Long id, BigDecimal balance) {
        super();
        this.setId(id);
        this.setBalance(balance);
    }

    public Account(Long id, User user, BigDecimal balance) {
        super();
        this.setId(id);
        this.setUser(user);
        this.setBalance(balance);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Account { " + "id=" + id +
                        ", user=" + user.toString() +
                     ", balance=" + balance + " }";
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        return id != null && id.equals(((Account) o).getId()) &&
               user != null && user.equals(((Account) o).getUser()) &&
               balance != null && balance.equals(((Account) o).getBalance());
    }
}
