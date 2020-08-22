package ba.unsa.etf.zavrsni.server.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "registration_plate_users")
public class RegistrationPlateUser {
    @Id
    @GeneratedValue(generator = "registration_plate_user")
    @SequenceGenerator(
            name = "registration_plate_user",
            sequenceName = "registration_plate_sequence",
            initialValue = 100
    )
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "registration_plate_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RegistrationPlate registrationPlate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "application_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApplicationUser applicationUser;

    public RegistrationPlateUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationPlate getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(RegistrationPlate registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
