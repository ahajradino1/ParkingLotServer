package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByCvcAndCardNumber(String cvc, String cardNumber);
    List<BankAccount> findByCardNumber(String cardNumber);
}
