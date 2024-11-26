package ucd.comp3013j.ems.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucd.comp3013j.ems.model.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    
}
