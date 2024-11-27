package ucd.comp3013j.ems.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.TicketType;

import java.util.HashMap;
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
    private String premium;
    private String standard;
    private String economy;

    public Map<TicketType, Integer> getSeatsByLevel() {
        Map<TicketType, Integer> seats = new HashMap<>();
        seats.put(TicketType.PREMIUM, Integer.parseInt(premium));
        seats.put(TicketType.STANDARD, Integer.parseInt(standard));
        seats.put(TicketType.ECONOMY, Integer.parseInt(economy));
        return seats;
    }
}
