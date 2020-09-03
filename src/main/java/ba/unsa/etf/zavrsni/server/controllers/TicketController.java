package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.models.Ticket;
import ba.unsa.etf.zavrsni.server.requests.ActiveTicketRequest;
import ba.unsa.etf.zavrsni.server.responses.TicketDataResponse;
import ba.unsa.etf.zavrsni.server.security.CurrentUser;
import ba.unsa.etf.zavrsni.server.security.UserPrincipal;
import ba.unsa.etf.zavrsni.server.service.BankAccountUserService;
import ba.unsa.etf.zavrsni.server.service.TicketService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public List<TicketDataResponse> getAllTickets(@CurrentUser UserPrincipal currentUser) {
        return ticketService.findAllTicketsByApplicationUserId(currentUser.getId());
    }

    //those are tickets for currently parked cars
    @PostMapping("/active")
    public List<TicketDataResponse> getActiveTickets(@Valid @RequestBody ActiveTicketRequest activeTicketRequest, @CurrentUser UserPrincipal currentUser) {
        return ticketService.findAllTicketsByUserAndDate(currentUser.getId(), activeTicketRequest.getCurrentDate());
    }

    //list of active tickets for certain car
    @PostMapping("/active/{registrationPlateId}")
    public List<TicketDataResponse> getActiveTicketsForCar(@PathVariable Long registrationPlateId, @Valid @RequestBody ActiveTicketRequest activeTicketRequest, @CurrentUser UserPrincipal currentUser) {
        return ticketService.findAllTicketsByUserAndDateAndRegNo(currentUser.getId(), activeTicketRequest.getCurrentDate(), registrationPlateId);
    }
}
