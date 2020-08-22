package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.Bank;
import ba.unsa.etf.zavrsni.server.repositories.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    public BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank find(String bankName) {
        return bankRepository.findByBankName(bankName);
    }

    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }
}
