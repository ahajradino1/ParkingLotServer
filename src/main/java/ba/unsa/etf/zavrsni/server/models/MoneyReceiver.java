package ba.unsa.etf.zavrsni.server.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "money_receivers")
public class MoneyReceiver {
    @Id
    @GeneratedValue(generator = "money_receiver_generator")
    @SequenceGenerator(
            name = "money_receiver_generator",
            sequenceName = "money_receiver_sequence",
            initialValue = 100
    )
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bank_account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BankAccount bankAccount;

    @Column(columnDefinition = "text")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
