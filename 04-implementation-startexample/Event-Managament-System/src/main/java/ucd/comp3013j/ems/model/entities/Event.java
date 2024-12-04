package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.TicketType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Event entity representing performance or show events in the system.
 * 
 * Key features:
 * - Manages event basic information (name, description, date/time)
 * - Links to venue and organiser
 * - Handles tiered pricing structure
 * - Tracks seat availability
 * - Maintains ticket sales records
 * 
 * The event entity uses @ElementCollection for efficient storage of
 * price levels and seat availability using Map structures.
 * 
 * @see Venue
 * @see Organiser
 * @see Ticket
 * @see TicketType
 */
@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "organiser_id")
    private Organiser organiser;


    @ElementCollection
    @CollectionTable(name = "event_prices_by_level")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "price")
    private Map<TicketType, BigDecimal> pricesByLevel;


    @ElementCollection
    @CollectionTable(name = "event_remaining_seats")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "remaining_count")
    private Map<TicketType, Integer> remainingSeats;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
} 