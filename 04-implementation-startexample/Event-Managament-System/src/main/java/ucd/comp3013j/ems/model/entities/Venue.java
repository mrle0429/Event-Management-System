package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.TicketType;

import java.util.List;
import java.util.Map;

/**
 * Venue entity representing event locations in the system.
 * 
 * Key features:
 * - Manages venue basic information (name, address, description)
 * - Configures seating capacity for different ticket types
 * - Stores contact information
 * - Tracks events scheduled at the venue
 * 
 * Uses @ElementCollection for efficient storage of seating capacity
 * information across different ticket types.
 * 
 * @see Event
 * @see TicketType
 */
@Entity
@Data
@NoArgsConstructor
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String description;



    private String contactPhone;
    private String contactEmail;
    private String contactName;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Event> events;


    @ElementCollection
    @CollectionTable(name = "venue_seats_by_level")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "seat_count")
    private Map<TicketType, Integer> seatsByLevel;
} 