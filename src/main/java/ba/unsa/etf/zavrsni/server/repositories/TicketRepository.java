package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.Ticket;
import ba.unsa.etf.zavrsni.server.util.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByApplicationUser_IdAndPaymentStatus(Long userId, PaymentStatus paymentStatus);
    List<Ticket> findByApplicationUser_IdAndCreatedAtAndPaymentStatus(Long userId, Date currentDate, PaymentStatus paymentStatus);
}
