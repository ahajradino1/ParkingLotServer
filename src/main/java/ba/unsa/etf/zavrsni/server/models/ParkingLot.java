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

    @Column(name = "price")
    private Double price;

    public ParkingLot() {
    }

    public ParkingLot(String zoneCode, String streetAddress, String municipality, Double price) {
        this.zoneCode = zoneCode;
        this.streetAddress = streetAddress;
        this.municipality = municipality;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
