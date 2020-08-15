package ba.unsa.etf.zavrsni.server.models;

import javax.persistence.*;

@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(generator = "bank_as_client_generator")
    @SequenceGenerator(
            name = "bank_as_client_generator",
            sequenceName = "bank_as_client_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(name = "bankName")
    private String bankName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
