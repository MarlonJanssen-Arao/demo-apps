package tech.arao.minibank.data.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 3606237742805308208L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "transaction_type", nullable = false)
    private String transactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @Column(nullable = false)
    private LocalDateTime timestamp;


    protected Transaction() {
        super();
    }

    public Transaction(Long id, User user, String transactionType, Account account) {
        super();
        this.setId(id);
        this.setUser(user);
        this.setTransactionType(transactionType);
        this.setAccount(account);
        this.setTimestamp(LocalDateTime.now());
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Date getTimestampAsDate() {
        return java.util.Date.from(timestamp.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = new java.sql.Timestamp(timestamp.getTime()).toLocalDateTime();
    }


    @Override
    public String toString() {
        return "Transaction { " + "id=" + id +
                            ", userId=" + user +
                   ", transactionType='" + transactionType + '\'' +
                         ", accountId=" + account +
                         ", timestamp=" + timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")) + " }";
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        return id != null && id.equals(((Transaction) o).getId()) &&
               user != null && user.equals(((Transaction) o).getUser()) &&
               transactionType != null && transactionType.equals(((Transaction) o).getTransactionType()) &&
               account != null && account.equals(((Transaction) o).getAccount()) &&
               timestamp != null && timestamp.equals(((Transaction) o).getTimestamp()) ;
    }
}
