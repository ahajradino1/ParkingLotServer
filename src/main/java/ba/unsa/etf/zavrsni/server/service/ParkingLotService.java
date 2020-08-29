package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.ParkingLot;
import ba.unsa.etf.zavrsni.server.repositories.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }


    public ParkingLot findById(Long parkingLotId) {
        return parkingLotRepository.findById(parkingLotId).get();
    }
}
