package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.*;
import ba.unsa.etf.zavrsni.server.requests.PaymentRequest;
import ba.unsa.etf.zavrsni.server.responses.PaymentResponse;
import ba.unsa.etf.zavrsni.server.security.CurrentUser;
import ba.unsa.etf.zavrsni.server.security.UserPrincipal;
import ba.unsa.etf.zavrsni.server.service.*;
import ba.unsa.etf.zavrsni.server.util.PaymentStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final TicketService ticketService;
    private final BankAccountUserService bankAccountUserService;
    private final ParkingLotService parkingLotService;
    private final RegistrationPlateService registrationPlateService;
    private final MoneyReceiverService moneyReceiverService;

    public PaymentController(TicketService ticketService, BankAccountUserService bankAccountUserService, ParkingLotService parkingLotService, RegistrationPlateService registrationPlateService, MoneyReceiverService moneyReceiverService) {
        this.ticketService = ticketService;
        this.bankAccountUserService = bankAccountUserService;
        this.parkingLotService = parkingLotService;
        this.registrationPlateService = registrationPlateService;
        this.moneyReceiverService = moneyReceiverService;
    }

    @PostMapping("/submit")
    public PaymentResponse processPayments(@Valid @RequestBody PaymentRequest paymentRequest, @CurrentUser UserPrincipal userPrincipal) {

        ParkingLot parkingLot = parkingLotService.findById(paymentRequest.getParkingLotId());
        if(parkingLot == null)
            throw new ResourceNotFoundException("Parking lot does not exist");
        RegistrationPlate registrationPlate = registrationPlateService.findById(paymentRequest.getRegistrationPlateId());
        if(registrationPlate == null)
            throw new ResourceNotFoundException("Registration plate does not exist");
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(userPrincipal.getId());
        Ticket ticket = new Ticket(registrationPlate, parkingLot, applicationUser, paymentRequest.getStartingTime(), paymentRequest.getEndingTime(), paymentRequest.getPrice(), PaymentStatus.PENDING);

        BankAccountUser bankAccountUser = bankAccountUserService.findBankAccountUserById(paymentRequest.getBankAccountId());
        if(bankAccountUser == null)
            return new PaymentResponse(PaymentStatus.CANCELED, "Nonexistent bank account!");
        PaymentResponse paymentResponse = bankAccountUserService.getPaymentResult(paymentRequest.getBankAccountId(), userPrincipal.getId(), ticket.getPrice());
        MoneyReceiver moneyReceiver = moneyReceiverService.find("Money receiver");
        if(paymentResponse.getPaymentStatus().equals(PaymentStatus.PAID)) {
            ticket.setPaymentStatus(PaymentStatus.PAID);
            ticket.setBankAccount(bankAccountUser.getBankAccount());
            moneyReceiver.getBankAccount().putIntoAccount(ticket.getPrice());
            ticketService.save(ticket);
        }
        return paymentResponse;
    }
}
