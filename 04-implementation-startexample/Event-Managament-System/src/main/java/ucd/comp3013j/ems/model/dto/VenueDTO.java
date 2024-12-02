package ucd.comp3013j.ems.model.dto;

import lombok.Data;
import ucd.comp3013j.ems.model.enums.TicketType;

import java.util.Map;

@Data
public class VenueDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String contactPhone;
    private String contactEmail;
    private String contactName;
    private Map<TicketType, Integer> seatsByLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
} 