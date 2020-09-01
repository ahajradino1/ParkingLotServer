package ba.unsa.etf.zavrsni.server.models;

import javax.persistence.*;

@Entity
@Table(name = "parking_lots")
public class ParkingLot extends AuditModel {

    @Id
    @GeneratedValue(generator = "parking_generator")
    @SequenceGenerator(
            name = "parking_generator",
            sequenceName = "parking_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String zoneCode;

    @Column(columnDefinition = "text")
    private String streetAddress;

    @Column(columnDefinition = "text")
    private String municipality;

    @Column(columnDefinition = "text")
    private String workDays;

    @Column(columnDefinition = "text")
    private String workTime;

    @Column(name = "price")
    private Double price;

    public ParkingLot() {
    }

    public ParkingLot(String zoneCode, String streetAddress, String municipality, String workDays, String workTime, Double price) {
        this.zoneCode = zoneCode;
        this.streetAddress = streetAddress;
        this.municipality = municipality;
        this.workDays = workDays;
        this.workTime = workTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
