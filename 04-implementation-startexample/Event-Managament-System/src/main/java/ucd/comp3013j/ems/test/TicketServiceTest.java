package ucd.comp3013j.ems.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Ticket;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.repos.EventRepository;
import ucd.comp3013j.ems.model.repos.TicketRepository;
import ucd.comp3013j.ems.model.services.TicketService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private TicketService ticketService;

    private Event event;
    private Customer customer;
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 创建 Customer 对象
        customer = new Customer();
        customer.setEmail("test@example.com");

        // 创建 Event 对象
        event = new Event();
        event.setId(1L);
        event.setName("Sample Event");
        event.setRemainingSeats(new HashMap<>());
        event.getRemainingSeats().put(TicketType.PREMIUM, 100);  // 设置 100 张常规票
        event.setPricesByLevel(new HashMap<>());
        event.getPricesByLevel().put(TicketType.PREMIUM, BigDecimal.valueOf(50.0));  // 设置票价

        // 创建 Ticket 对象
        ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setCustomer(customer);
        ticket.setType(TicketType.PREMIUM);
        ticket.setPurchaseTime(LocalDateTime.now());
        ticket.setPrice(BigDecimal.valueOf(50.0));
    }

    // 测试购买单张票
    @Test
    public void testPurchaseTicket_Success() {
        // 模拟 ticketRepository 和 eventRepository
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // 调用 purchaseTicket 方法
        Ticket purchasedTicket = ticketService.purchaseTicket(event, customer, TicketType.PREMIUM);

        assertNotNull(purchasedTicket);
        assertEquals(TicketType.PREMIUM, purchasedTicket.getType());
        assertEquals(BigDecimal.valueOf(50.0), purchasedTicket.getPrice());
        assertEquals(99, event.getRemainingSeats().get(TicketType.PREMIUM));  // 剩余票数应减少 1

        verify(ticketRepository, times(1)).save(any(Ticket.class));
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testPurchaseTicket_NoTicketsAvailable() {
        // 设置剩余票数为 0
        event.getRemainingSeats().put(TicketType.PREMIUM, 0);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ticketService.purchaseTicket(event, customer, TicketType.PREMIUM);
        });

        assertEquals("No tickets available for this type", exception.getMessage());
    }

    // 测试获取用户所有票
    @Test
    public void testGetCustomerTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        when(ticketRepository.findByCustomer(customer)).thenReturn(tickets);

        List<Ticket> result = ticketService.getCustomerTickets(customer);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TicketType.PREMIUM, result.get(0).getType());
        verify(ticketRepository, times(1)).findByCustomer(customer);
    }

    // 测试获取活动所有票
    @Test
    public void testGetEventTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        when(ticketRepository.findByEvent(event)).thenReturn(tickets);

        List<Ticket> result = ticketService.getEventTickets(event);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TicketType.PREMIUM, result.get(0).getType());
        verify(ticketRepository, times(1)).findByEvent(event);
    }

    // 测试检查票是否可用
    @Test
    public void testIsTicketAvailable() {
        boolean isAvailable = ticketService.isTicketAvailable(event, TicketType.PREMIUM);
        assertTrue(isAvailable);
    }

    @Test
    public void testIsTicketUnavailable() {
        // 设置剩余票数为 0
        event.getRemainingSeats().put(TicketType.PREMIUM, 0);

        boolean isAvailable = ticketService.isTicketAvailable(event, TicketType.PREMIUM);
        assertFalse(isAvailable);
    }

    // 测试获取单个票
    @Test
    public void testGetTicket() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicket(1L);

        assertNotNull(result);
        assertEquals(TicketType.PREMIUM, result.getType());
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTicket_NotFound() {
        when(ticketRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ticketService.getTicket(2L);
        });

        assertEquals("Ticket not found", exception.getMessage());
    }

    // 测试批量购买票
    @Test
    public void testPurchaseTickets_Success() {
        // 设置剩余票数为 5
        event.getRemainingSeats().put(TicketType.PREMIUM, 5);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        List<Ticket> purchasedTickets = ticketService.purchaseTickets(event, customer, TicketType.PREMIUM, 1);

        assertNotNull(purchasedTickets);
        assertEquals(1, purchasedTickets.size());
        assertEquals(TicketType.PREMIUM, purchasedTickets.get(0).getType());
        assertEquals(4, event.getRemainingSeats().get(TicketType.PREMIUM));  // 剩余票数应减少 1

        verify(ticketRepository, times(1)).save(any(Ticket.class));
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testPurchaseTickets_InsufficientSeats() {
        // 设置剩余票数为 0
        event.getRemainingSeats().put(TicketType.PREMIUM, 0);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ticketService.purchaseTickets(event, customer, TicketType.PREMIUM, 2);
        });

        assertEquals("Only 0 seats remaining for PREMIUM", exception.getMessage());
    }

    // 测试获取即将开始的票
    @Test
    public void testGetUpcomingTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        when(ticketRepository.findByCustomer(customer)).thenReturn(tickets);

        List<Ticket> result = ticketService.getUpcomingTickets(customer);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ticketRepository, times(1)).findByCustomer(customer);
    }
}