package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    
    @ManyToOne
    @JoinColumn(name = "organiser_id")
    private Organiser organiser;
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    
    @ElementCollection
    @CollectionTable(name = "event_prices_by_level")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "price")
    private Map<TicketLevel, BigDecimal> pricesByLevel;
    
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    
    @ElementCollection
    @CollectionTable(name = "event_remaining_seats")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "remaining_count")
    private Map<TicketLevel, Integer> remainingSeats;
} 