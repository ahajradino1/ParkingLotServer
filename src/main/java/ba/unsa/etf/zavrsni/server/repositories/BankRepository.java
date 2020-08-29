package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByBankName(String bankName);
    List<Bank> findAll();
}
