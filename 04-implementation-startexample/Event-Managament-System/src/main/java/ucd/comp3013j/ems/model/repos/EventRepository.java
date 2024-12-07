package ucd.comp3013j.ems.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByOrganiser(Organiser organiser);


    List<Event> findByOrganiserId(Long organiserId);


    List<Event> findByVenueIdAndDate(Long venueId, Date date);


    List<Event> findByNameContaining(String name);


    List<Event> findByDateAfter(Date date);


    List<Event> findByDateBetween(Date startDate, Date endDate);


    @Query("SELECT e FROM Event e JOIN e.pricesByLevel p WHERE KEY(p) = :ticketType AND VALUE(p) BETWEEN :minPrice AND :maxPrice")
    List<Event> findByTicketPriceRange(@Param("ticketType") String ticketType,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice);


    @Query("SELECT e FROM Event e WHERE FUNCTION('DATE', e.date) >= FUNCTION('DATE', CURRENT_DATE) ORDER BY e.date ASC")
    List<Event> findAvailableEvents();
}
