package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.Ticket;
import ba.unsa.etf.zavrsni.server.util.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByApplicationUser_IdAndPaymentStatus(Long userId, PaymentStatus paymentStatus);
    @Query(value = "from Ticket t where t.applicationUser.id = :id AND t.paymentStatus = :paymentStatus AND :currentDate BETWEEN t.startingTime AND t.endingTime")
    List<Ticket> getAllActive(@Param("id") Long id, @Param("currentDate") Date currentDate, @Param("paymentStatus") PaymentStatus paymentStatus);

    @Query(value = "from Ticket t where t.applicationUser.id = :id AND t.paymentStatus = :paymentStatus AND t.registrationPlate.id = :registrationPlateId AND :currentDate BETWEEN t.startingTime AND t.endingTime")
    List<Ticket> getAllActiveForCar(@Param("id") Long id, @Param("currentDate") Date currentDate, @Param("registrationPlateId") Long registrationPlateId, @Param("paymentStatus") PaymentStatus paymentStatus);
}
