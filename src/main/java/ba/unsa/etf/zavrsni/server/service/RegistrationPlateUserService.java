package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.RegistrationPlate;
import ba.unsa.etf.zavrsni.server.models.RegistrationPlateUser;
import ba.unsa.etf.zavrsni.server.repositories.RegistrationPlateUserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationPlateUserService {
    private final RegistrationPlateUserRepository registrationPlateUserRepository;

    public RegistrationPlateUserService(RegistrationPlateUserRepository registrationPlateUserRepository) {
        this.registrationPlateUserRepository = registrationPlateUserRepository;
    }

    public RegistrationPlateUser save(RegistrationPlateUser registrationPlateUser) {
        return registrationPlateUserRepository.save(registrationPlateUser);
    }

    public List<RegistrationPlate> findRegistrationTables(Long userId) {
        return registrationPlateUserRepository.findAllByApplicationUser_Id(userId)
                .stream()
                .map(RegistrationPlateUser::getRegistrationPlate)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        registrationPlateUserRepository.deleteById(id);
    }

    public List<RegistrationPlateUser> findAllByRegistrationPlate_Number(String registrationNumber) {
        return registrationPlateUserRepository.findAllByRegistrationPlate_RegistrationNumber(registrationNumber);
    }

    public boolean existsByIdAndUserId(Long platesId, Long userId) {
        return registrationPlateUserRepository.existsByIdAndApplicationUser_Id(platesId, userId);
    }
}

