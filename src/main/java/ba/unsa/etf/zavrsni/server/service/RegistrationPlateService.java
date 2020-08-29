package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.ParkingLot;
import ba.unsa.etf.zavrsni.server.models.RegistrationPlate;
import ba.unsa.etf.zavrsni.server.repositories.RegistrationPlateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationPlateService {
    private final RegistrationPlateRepository registrationPlateRepository;

    public RegistrationPlateService(RegistrationPlateRepository registrationPlateRepository) {
        this.registrationPlateRepository = registrationPlateRepository;
    }

    public List<RegistrationPlate> findByRegistrationNumber(String registrationNumber) {
        return registrationPlateRepository.findByRegistrationNumber(registrationNumber);
    }

    public RegistrationPlate save(RegistrationPlate registrationPlate) {
        return registrationPlateRepository.save(registrationPlate);
    }

    public void delete(Long plateId) {
        registrationPlateRepository.deleteById(plateId);
    }

    public RegistrationPlate findById(Long registrationPlateId) {
        return registrationPlateRepository.findById(registrationPlateId).get();
    }
}
