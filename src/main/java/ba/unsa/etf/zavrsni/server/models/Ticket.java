package ba.unsa.etf.zavrsni.server.models;

import ba.unsa.etf.zavrsni.server.util.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket extends AuditModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_account_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BankAccount bankAccount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "registration_plate_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RegistrationPlate registrationPlate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parking_lot_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ParkingLot parkingLot;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "application_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApplicationUser applicationUser;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="Europe/Sarajevo")
    @Column(name = "starting_time")
    private Date startingTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="Europe/Sarajevo")
    @Column(name = "ending_time")
    private Date endingTime;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name="payment_status")
    private PaymentStatus paymentStatus;

    public Ticket() {
    }

    public Ticket(RegistrationPlate registrationPlate, ParkingLot parkingLot, ApplicationUser applicationUser, Date startingTime, Date endingTime, Double price, PaymentStatus paymentStatus) {
        this.registrationPlate = registrationPlate;
        this.parkingLot = parkingLot;
        this.applicationUser = applicationUser;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public RegistrationPlate getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(RegistrationPlate registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
