package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class Venue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String address;
    private String description;
    private Integer capacity;
    
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Event> events;
    
    private String contactPhone;
    private String contactEmail;
    
    @ElementCollection
    @CollectionTable(name = "venue_seats_by_level")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "seat_count")
    private Map<TicketLevel, Integer> seatsByLevel;
} 