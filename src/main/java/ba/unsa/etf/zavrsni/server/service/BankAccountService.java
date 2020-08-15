package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.BankAccount;
import ba.unsa.etf.zavrsni.server.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> find(String cvc, String cardNumber) {
        return bankAccountRepository.findByCvcAndCardNumber(cvc, cardNumber);
    }

    public List<BankAccount> findByCardNumber(String cardNumber) {
        return bankAccountRepository.findByCardNumber(cardNumber);
    }

    public BankAccount save(BankAccount bankAccount) {
        return  bankAccountRepository.save(bankAccount);
    }
}

