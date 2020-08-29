package ba.unsa.etf.zavrsni.server.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class PaymentRequest {
    @NotNull
    private Long bankAccountId;

    @NotNull
    private Long parkingLotId;

    @NotNull
    private Long registrationPlateId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Sarajevo")
    private Date startingTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Sarajevo")
    private Date endingTime;

    @NotNull
    @DecimalMin("0.0")
    private Double price;

    public PaymentRequest(@NotNull Long bankAccountId, @NotNull Long parkingLotId, @NotNull Long registrationPlateId, Date startingTime, Date endingTime, @NotNull @DecimalMin("0.0") Double price) {
        this.bankAccountId = bankAccountId;
        this.parkingLotId = parkingLotId;
        this.registrationPlateId = registrationPlateId;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.price = price;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Long getRegistrationPlateId() {
        return registrationPlateId;
    }

    public void setRegistrationPlateId(Long registrationPlateId) {
        this.registrationPlateId = registrationPlateId;
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
}
