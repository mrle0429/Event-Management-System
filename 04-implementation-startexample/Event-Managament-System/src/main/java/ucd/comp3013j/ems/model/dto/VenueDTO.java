package ucd.comp3013j.ems.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.TicketType;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueDTO {
    private String name;
    private String address;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String description;
    private Map<TicketType, Integer> seatsByLevel;
}
