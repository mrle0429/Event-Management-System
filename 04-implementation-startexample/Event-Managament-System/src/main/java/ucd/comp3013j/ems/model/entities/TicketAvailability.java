package ucd.comp3013j.ems.model.entities;

import ucd.comp3013j.ems.model.enums.TicketType;

/**
 * 用于表示某个票种的可用票数。这个类通常用于表示一个 Venue 或 Event 中不同票种的总可用票数。
 */
public class TicketAvailability {
    private Long id;
    private TicketType ticketType;
    private int availableTickets;
    
}
