package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.MoneyReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyReceiverRepository extends JpaRepository<MoneyReceiver, Long> {
    MoneyReceiver findByName(String name);
}
