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
    private String address;

    @Column(name = "free_spots")
    private int freeSpots;

    @Column(name = "price")
    private Double price;

    @Column(name = "max_stoptime")
    private int maxStoptime; //in minutes

    public ParkingLot() {
    }

    public ParkingLot(String zoneCode, String address, int freeSpots, Double price, int maxStoptime) {
        this.zoneCode = zoneCode;
        this.address = address;
        this.freeSpots = freeSpots;
        this.price = price;
        this.maxStoptime = maxStoptime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(int freeSpots) {
        this.freeSpots = freeSpots;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMaxStoptime() {
        return maxStoptime;
    }

    public void setMaxStoptime(int maxStoptime) {
        this.maxStoptime = maxStoptime;
    }

    public void freeParkingSpot() {
        freeSpots++;
    }

    public void takeParkingSpot() {
        freeSpots--;
    }

}
