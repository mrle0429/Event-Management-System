package ucd.comp3013j.ems.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer(Customer customer);

    List<Ticket> findByCustomerId(Long customerId);

    List<Ticket> findByEvent(Event event);

    List<Ticket> findByEventAndCustomerAndPurchaseTime(Event event, Customer customer, LocalDateTime purchaseTime);

    @Query("SELECT t.type, COUNT(t) FROM Ticket t WHERE t.event.id = :eventId GROUP BY t.type")
    List<Object[]> countSoldTicketsByEventIdAndType(Long eventId);

}
