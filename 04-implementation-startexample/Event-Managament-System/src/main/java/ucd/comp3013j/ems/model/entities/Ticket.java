package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private TicketLevel level;
    
    @Column(unique = true)
    private String ticketCode;
    
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    
    @PrePersist
    private void generateTicketCode() {
        this.ticketCode = UUID.randomUUID().toString();
    }
} 