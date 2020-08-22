package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.RegistrationPlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationPlateRepository extends JpaRepository<RegistrationPlate, Long> {

    List<RegistrationPlate> findByRegistrationNumber(String registrationNumber);
}
