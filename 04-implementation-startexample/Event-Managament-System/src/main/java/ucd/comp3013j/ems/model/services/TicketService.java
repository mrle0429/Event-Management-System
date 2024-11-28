package ucd.comp3013j.ems.model.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Ticket;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.repos.EventRepository;
import ucd.comp3013j.ems.model.repos.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private EventRepository eventRepository;

    /**
     * 购买票
     */
    @Transactional
    public Ticket purchaseTicket(Event event, Customer customer, TicketType ticketType) {
        // 检查剩余座位
        Map<TicketType, Integer> remainingSeats = event.getRemainingSeats();
        Integer remaining = remainingSeats.get(ticketType);
        
        if (remaining == null || remaining <= 0) {
            throw new RuntimeException("No tickets available for this type");
        }

        // 创建票据
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setCustomer(customer);
        ticket.setType(ticketType);
        ticket.setPurchaseTime(LocalDateTime.now());
        ticket.setPrice(event.getPricesByLevel().get(ticketType));

        // 更新剩余座位数
        remainingSeats.put(ticketType, remaining - 1);
        event.setRemainingSeats(remainingSeats);
        
        // 保存更改
        eventRepository.save(event);
        return ticketRepository.save(ticket);
    }

    /**
     * 获取用户的所有票
     */
    public List<Ticket> getCustomerTickets(Customer customer) {
        return ticketRepository.findByCustomer(customer);
    }

    /**
     * 获取活动的所有售出票
     */
    public List<Ticket> getEventTickets(Event event) {
        return ticketRepository.findByEvent(event);
    }

    /**
     * 检查票是否可用
     */
    public boolean isTicketAvailable(Event event, TicketType ticketType) {
        Map<TicketType, Integer> remainingSeats = event.getRemainingSeats();
        Integer remaining = remainingSeats.get(ticketType);
        return remaining != null && remaining > 0;
    }

    /**
     * 获取单个票据
     */
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
}
