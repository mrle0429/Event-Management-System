package ucd.comp3013j.ems.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucd.comp3013j.ems.model.entities.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // 根据顾客ID查找所有票
    List<Ticket> findByCustomerId(Long customerId);
}
