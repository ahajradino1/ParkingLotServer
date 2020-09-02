package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.ApplicationUser;
import ba.unsa.etf.zavrsni.server.models.Bank;
import ba.unsa.etf.zavrsni.server.models.BankAccount;
import ba.unsa.etf.zavrsni.server.models.BankAccountUser;
import ba.unsa.etf.zavrsni.server.requests.BankAccountRequest;
import ba.unsa.etf.zavrsni.server.responses.BankAccountDataResponse;
import ba.unsa.etf.zavrsni.server.responses.BankAccountManageResponse;
import ba.unsa.etf.zavrsni.server.security.CurrentUser;
import ba.unsa.etf.zavrsni.server.security.UserPrincipal;
import ba.unsa.etf.zavrsni.server.service.ApplicationUserService;
import ba.unsa.etf.zavrsni.server.service.BankAccountService;
import ba.unsa.etf.zavrsni.server.service.BankAccountUserService;
import ba.unsa.etf.zavrsni.server.service.BankService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {
    private final BankAccountUserService bankAccountUserService;
    private final BankAccountService bankAccountService;
    private final BankService bankService;
    private final ApplicationUserService applicationUserService;
    private final PasswordEncoder passwordEncoder;

    public BankAccountController(BankAccountUserService bankAccountUserService, BankAccountService bankAccountService, BankService bankService, ApplicationUserService applicationUserService, PasswordEncoder passwordEncoder) {
        this.bankAccountUserService = bankAccountUserService;
        this.bankAccountService = bankAccountService;
        this.bankService = bankService;
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
    public List<BankAccountDataResponse> getBankAccounts(@CurrentUser UserPrincipal currentUser) {
       return bankAccountUserService.findBankAccounts(currentUser.getId());
    }

    @PostMapping("/add")
    public BankAccountManageResponse addBankAccount(@Valid @RequestBody BankAccountRequest bankAccountRequest, @CurrentUser UserPrincipal currentUser) {
        List<BankAccount> bankAccounts = bankAccountService.findByCardNumber(bankAccountRequest.getCardNumber());

//        if(bankAccounts.isEmpty())
//            throw new ResourceNotFoundException("Bank account is not valid!");
        if(!bankAccounts.isEmpty() && !bankAccountUserService.findAllByBankAccount_CardNumber(bankAccounts.get(0).getCardNumber()).isEmpty())
            throw new ResourceNotFoundException("Bank account is in use!");

  //      BankAccount account = bankAccounts.get(0);
        //entered cvc doesn't math encrypted cvc in database
//        if (!passwordEncoder.matches(bankAccountRequest.getCvc(), account.getCvc())){
//            throw new ResourceNotFoundException("Bank account is not valid!");

//        ApplicationUser currUser = applicationUserService.find(currentUser.getId());
//        if(!account.getAccountOwner().equals(currUser.getFirstName()+" "+currUser.getLastName())){
//            return new BankAccountManageResponse(false, "User and account owner do not match");
//        }
        BankAccountUser bankAccountUser = new BankAccountUser();
        ApplicationUser user = new ApplicationUser();
        user.setId(currentUser.getId());
     //   bankAccountUser.setBankAccount(bankAccounts.get(0));



        BankAccount addedAcc = new BankAccount(bankAccountRequest.getAccountOwner(), bankAccountRequest.getCvc(), bankAccountRequest.getCardNumber(), bankAccountRequest.getExpiryDate(), 5000.0);
        addedAcc.setCvc(passwordEncoder.encode(bankAccountRequest.getCvc()));
        Bank bank = bankService.find(bankAccountRequest.getBankName());
        if(bank != null)
            addedAcc.setBank(bank);

        bankAccountUser.setBankAccount(addedAcc);
        bankAccountUser.setApplicationUser(user);
        bankAccountService.save(addedAcc);
        bankAccountUserService.save(bankAccountUser);

        return new BankAccountManageResponse(true, "Succefully added account");
    }

    @DeleteMapping("/delete/{accountId}")
    public BankAccountManageResponse deleteBankAccounts(@PathVariable Long accountId, @CurrentUser UserPrincipal currentUser){
        if(!bankAccountUserService.existsByIdAndUserId(accountId, currentUser.getId())){
            return new BankAccountManageResponse(false, "Account does not exist!");
        }
        bankAccountUserService.delete(accountId);
        return new BankAccountManageResponse(true, "Successful deletion!");
    }
}
