package ba.unsa.etf.zavrsni.server.models;

import javax.persistence.*;

@Entity
@Table(name = "registration_plates")
public class RegistrationPlate extends AuditModel {
    @Id
    @GeneratedValue(generator = "plates_generator")
    @SequenceGenerator(
            name = "plates_generator",
            sequenceName = "plates_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String registrationNumber;

    public RegistrationPlate() {
    }

    public RegistrationPlate(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
