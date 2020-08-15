package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.BankAccount;
import ba.unsa.etf.zavrsni.server.models.BankAccountUser;
import ba.unsa.etf.zavrsni.server.repositories.BankAccountUserRepository;
import ba.unsa.etf.zavrsni.server.responses.BankAccountDataResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountUserService {
    private final BankAccountUserRepository bankAccountUserRepository;

    public BankAccountUserService(BankAccountUserRepository bankAccountUserRepository) {
        this.bankAccountUserRepository = bankAccountUserRepository;
    }

    public BankAccountUser save(BankAccountUser bankAccountUser) {
        return bankAccountUserRepository.save(bankAccountUser);
    }

    public List<BankAccountDataResponse> findBankAccounts(Long userId) {
        return bankAccountUserRepository.findAllByApplicationUser_Id(userId)
                .stream()
                .map(bankAccountUser -> {
                    BankAccount bankAccount = bankAccountUser.getBankAccount();
                    return new BankAccountDataResponse(bankAccountUser.getId(), bankAccount.getAccountOwner(), bankAccount.getBank().getBankName(), bankAccount.getExpiryDate(), bankAccount.getCardNumber());
                })
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        bankAccountUserRepository.deleteById(id);
    }

    public boolean existsByIdAndUserId(Long accountId, Long userId) {
        return bankAccountUserRepository.existsByIdAndApplicationUser_Id(accountId, userId);
    }

    public List<BankAccountUser> findAllByBankAccount_CardNumber(String cardNumber) {
        return bankAccountUserRepository.findAllByBankAccount_CardNumber(cardNumber);
    }

    public BankAccountUser findBankAccountUserById(Long id) {
        return bankAccountUserRepository.findBankAccountUserById(id);
    }

    public BankAccountUser findBankAccountUserByIdAndApplicationUserId(Long bankAccountUserId, Long applicationUserId) {
        return bankAccountUserRepository.findByIdAndApplicationUser_Id(bankAccountUserId,applicationUserId);
    }
}
