package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.models.Bank;
import ba.unsa.etf.zavrsni.server.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping("/banks")
    public List<Bank> getBanks() {
        return bankService.findAll();
    }

    @PostMapping("/banks")
    public Bank createBank(@Valid @RequestBody Bank bank) {
        return bankService.save(bank);
    }
}
