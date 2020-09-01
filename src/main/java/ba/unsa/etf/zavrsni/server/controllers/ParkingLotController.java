package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.models.ParkingLot;
import ba.unsa.etf.zavrsni.server.service.ParkingLotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/all")
    public List<ParkingLot> getParkingLots() {
        return parkingLotService.findAll();
    }
}
