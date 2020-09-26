package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.MoneyReceiver;
import ba.unsa.etf.zavrsni.server.repositories.MoneyReceiverRepository;
import org.springframework.stereotype.Service;

@Service
public class MoneyReceiverService {
    public final MoneyReceiverRepository moneyReceiverRepository;

    public MoneyReceiverService(MoneyReceiverRepository moneyReceiverRepository) {
        this.moneyReceiverRepository = moneyReceiverRepository;
    }

    public MoneyReceiver find(String name) {
        return moneyReceiverRepository.findByName(name);
    }
}
