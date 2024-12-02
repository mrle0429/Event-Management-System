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
import java.util.*;
import java.util.stream.Collectors;

/**
 * Ticket Management Service
 * Handles all business logic related to tickets, including:
 * - Ticket purchases and reservations
 * - Ticket availability checks
 * - Ticket queries and searches
 * - Ticket status management
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    /**
     * Purchases a single ticket for an event.
     * Validates seat availability before purchase.
     *
     * @param event Event to purchase ticket for
     * @param customer Customer making the purchase
     * @param ticketType Type of ticket being purchased
     * @return The created Ticket object
     * @throws RuntimeException if no tickets are available for the requested type
     */
    @Transactional
    public Ticket purchaseTicket(Event event, Customer customer, TicketType ticketType) {
        // Check remaining seats
        Map<TicketType, Integer> remainingSeats = event.getRemainingSeats();
        Integer remaining = remainingSeats.get(ticketType);

        if (remaining == null || remaining <= 0) {
            throw new RuntimeException("No tickets available for this type");
        }

        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setCustomer(customer);
        ticket.setType(ticketType);
        ticket.setPurchaseTime(LocalDateTime.now());
        ticket.setPrice(event.getPricesByLevel().get(ticketType));

        // Update remaining seats
        remainingSeats.put(ticketType, remaining - 1);
        event.setRemainingSeats(remainingSeats);

        // Save changes
        eventRepository.save(event);
        return ticketRepository.save(ticket);
    }

    /**
     * Retrieves all tickets owned by a specific customer.
     *
     * @param customer Customer to get tickets for
     * @return List of tickets owned by the customer
     */
    public List<Ticket> getCustomerTickets(Customer customer) {
        return ticketRepository.findByCustomer(customer);
    }

    /**
     * Retrieves all tickets sold for a specific event.
     *
     * @param event Event to get tickets for
     * @return List of tickets sold for the event
     */
    public List<Ticket> getEventTickets(Event event) {
        return ticketRepository.findByEvent(event);
    }

    /**
     * Checks if tickets are available for a specific event and ticket type.
     *
     * @param event Event to check
     * @param ticketType Type of ticket to check
     * @return true if tickets are available, false otherwise
     */
    public boolean isTicketAvailable(Event event, TicketType ticketType) {
        Map<TicketType, Integer> remainingSeats = event.getRemainingSeats();
        Integer remaining = remainingSeats.get(ticketType);
        return remaining != null && remaining > 0;
    }

    /**
     * Retrieves a specific ticket by its ID.
     *
     * @param ticketId Ticket ID to search for
     * @return The found Ticket object
     * @throws RuntimeException if ticket not found
     */
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    /**
     * Purchases multiple tickets of the same type for an event.
     * Validates seat availability before purchase.
     *
     * @param event Event to purchase tickets for
     * @param customer Customer making the purchase
     * @param ticketType Type of tickets being purchased
     * @param quantity Number of tickets to purchase
     * @return List of created Ticket objects
     * @throws RuntimeException if not enough tickets are available
     */
    @Transactional
    public List<Ticket> purchaseTickets(Event event, Customer customer, TicketType ticketType, int quantity) {
        Map<TicketType, Integer> remainingSeats = event.getRemainingSeats();
        Integer remaining = remainingSeats.get(ticketType);

        if (remaining == null) {
            throw new RuntimeException("Invalid ticket type");
        }

        if (remaining < quantity) {
            throw new RuntimeException("Only " + remaining + " seats remaining for " + ticketType);
        }

        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setCustomer(customer);
            ticket.setType(ticketType);
            ticket.setPurchaseTime(LocalDateTime.now());
            ticket.setPrice(event.getPricesByLevel().get(ticketType));
            tickets.add(ticketRepository.save(ticket));
        }

        // Update remaining seats
        remainingSeats.put(ticketType, remaining - quantity);
        event.setRemainingSeats(remainingSeats);
        eventRepository.save(event);

        return tickets;
    }

    /**
     * Retrieves tickets purchased at the same time for an event by a customer.
     *
     * @param event Event to search tickets for
     * @param customer Customer who purchased the tickets
     * @param purchaseTime Time when tickets were purchased
     * @return List of tickets matching the criteria
     */
    public List<Ticket> getTicketsByEventAndCustomerAndPurchaseTime(Event event, Customer customer, LocalDateTime purchaseTime) {
        return ticketRepository.findByEventAndCustomerAndPurchaseTime(event, customer, purchaseTime);
    }

    /**
     * Retrieves all upcoming tickets for a customer.
     * Upcoming tickets are those for events that haven't started yet.
     *
     * @param customer Customer to get tickets for
     * @return List of upcoming tickets
     */
    public List<Ticket> getUpcomingTickets(Customer customer) {
        LocalDateTime now = LocalDateTime.now();
        return ticketRepository.findByCustomer(customer).stream()
                .filter(ticket -> {
                    Date eventDate = ticket.getEvent().getDate();
                    Date eventTime = ticket.getEvent().getTime();
                    return combineDateTime(eventDate, eventTime).after(new Date());
                })
                .collect(Collectors.toList());
    }

    /**
     * Combines a date and time into a single Date object.
     * Helper method for comparing event dates and times.
     *
     * @param date Date component
     * @param time Time component
     * @return Combined Date object
     */
    private Date combineDateTime(Date date, Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(time);

        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));

        return calendar.getTime();
    }
}
