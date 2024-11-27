package ucd.comp3013j.ems.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.TicketType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private String name;
    private String description;
    private Date date;
    private Date time;
    private Long venueId;
    private Long organiserId;
    private Map<TicketType, BigDecimal> pricesByLevel;
    private Map<TicketType, Integer> remainingSeats;
}
