package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.BankAccount;
import ba.unsa.etf.zavrsni.server.models.RegistrationPlate;
import ba.unsa.etf.zavrsni.server.models.Ticket;
import ba.unsa.etf.zavrsni.server.repositories.TicketRepository;
import ba.unsa.etf.zavrsni.server.responses.TicketDataResponse;
import ba.unsa.etf.zavrsni.server.util.PaymentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDataResponse> findAllTicketsByApplicationUserId(Long userId) {
        return getTicketData(ticketRepository.findByApplicationUser_IdAndPaymentStatus(userId, PaymentStatus.PAID));
    }

    public List<TicketDataResponse> findAllTicketsByUserAndDate(Long userId, Date currentDate) {
        return getTicketData(ticketRepository.findByApplicationUser_IdAndCreatedAtAndPaymentStatus(userId, currentDate, PaymentStatus.PAID));
    }

    private List<TicketDataResponse> getTicketData(List<Ticket> ticketList) {
        return ticketList
                .stream()
                .map(ticket -> {
                    BankAccount bankAccount = ticket.getBankAccount();
                    RegistrationPlate registrationPlate = ticket.getRegistrationPlate();
                    return new TicketDataResponse(ticket.getId(), bankAccount.getCardNumber(), registrationPlate.getRegistrationNumber(), ticket.getParkingLot(), ticket.getStartingTime(), ticket.getEndingTime(), ticket.getPrice());
                })
                .collect(Collectors.toList());
    }

    public Ticket save(Ticket ticket) {
       return ticketRepository.save(ticket);
    }
}
