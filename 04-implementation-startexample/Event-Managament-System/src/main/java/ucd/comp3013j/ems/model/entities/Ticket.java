package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.TicketType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Ticket entity representing a purchased ticket in the system.
 * 
 * Features:
 * - Automatically generates unique ticket codes using UUID
 * - Records purchase timestamp and price
 * - Links to associated event and customer
 * - Supports different ticket types (PREMIUM, STANDARD, ECONOMY)
 * 
 * Uses @PrePersist to ensure each ticket has a unique identifier
 * before being persisted to the database.
 * 
 * @see Event
 * @see Customer
 * @see TicketType
 */
@Entity
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime purchaseTime;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(unique = true)
    private String ticketCode;


    @PrePersist
    private void generateTicketCode() {
        this.ticketCode = UUID.randomUUID().toString();
    }
} 