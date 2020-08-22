package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.RegistrationPlateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationPlateUserRepository extends JpaRepository<RegistrationPlateUser, Long> {
    List<RegistrationPlateUser> findAllByApplicationUser_Id(Long id);
    List<RegistrationPlateUser> findAllByRegistrationPlate_RegistrationNumber(String registrationNumber);
    boolean existsByIdAndApplicationUser_Id(Long platesId, Long userId);
}
