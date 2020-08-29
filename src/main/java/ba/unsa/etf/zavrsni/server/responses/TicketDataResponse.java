package ba.unsa.etf.zavrsni.server.responses;

import ba.unsa.etf.zavrsni.server.models.ParkingLot;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

public class TicketDataResponse {
    private UUID ticketId;
    private String cardNumber;
    private String registrationNumber;
    private ParkingLot parkingLot;
    @JsonFormat(timezone = "Europe/Sarajevo")
    private Date startingTime;
    @JsonFormat(timezone = "Europe/Sarajevo")
    private Date endingTime;
    private Double price;

    public TicketDataResponse() {
    }

    public TicketDataResponse(UUID ticketId, String cardNumber, String registrationNumber, ParkingLot parkingLot, Date startingTime, Date endingTime, Double price) {
        this.ticketId = ticketId;
        this.cardNumber = cardNumber;
        this.registrationNumber = registrationNumber;
        this.parkingLot = parkingLot;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.price = price;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
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
