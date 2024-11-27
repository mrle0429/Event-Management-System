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
    // 根据组织者查找活动
    List<Event> findByOrganiser(Organiser organiser);
    
    // 根据活动名称模糊查询
    List<Event> findByNameContaining(String name);
    
    // 查找指定日期之后的活动
    List<Event> findByDateAfter(Date date);
    
    // 查找指定日期范围内的活动
    List<Event> findByDateBetween(Date startDate, Date endDate);
    
    // 自定义查询：查找某个价格区间的活动
    @Query("SELECT e FROM Event e JOIN e.pricesByLevel p WHERE KEY(p) = :ticketType AND VALUE(p) BETWEEN :minPrice AND :maxPrice")
    List<Event> findByTicketPriceRange(@Param("ticketType") String ticketType, 
                                      @Param("minPrice") BigDecimal minPrice, 
                                      @Param("maxPrice") BigDecimal maxPrice);
    
    // 查找当前日期之后的活动，并按日期排序
    @Query("SELECT e FROM Event e WHERE FUNCTION('DATE', e.date) >= FUNCTION('DATE', CURRENT_DATE) ORDER BY e.date ASC")
    List<Event> findAvailableEvents();
}
