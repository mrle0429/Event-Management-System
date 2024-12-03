package ucd.comp3013j.ems.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ucd.comp3013j.ems.model.dto.EventDTO;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.repos.EventRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;
import ucd.comp3013j.ems.model.repos.VenueRepository;
import ucd.comp3013j.ems.model.services.EventService;

import java.util.*;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private VenueRepository venueRepository;

    @Mock
    private OrganiserRepository organiserRepository;

    @InjectMocks
    private EventService eventService;

    private EventDTO eventDTO;
    private Event event;
    private Venue venue;
    private Organiser organiser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 创建 EventDTO
        eventDTO = new EventDTO();
        eventDTO.setName("Sample Event");
        eventDTO.setDescription("Sample Description");
        eventDTO.setDate(new Date());
        eventDTO.setTime(new Date());
        eventDTO.setVenueId(1L);
        eventDTO.setOrganiserId(1L);
        eventDTO.setRemainingSeats(new HashMap<>() {{
            put(TicketType.PREMIUM, 50);
        }});

        // 创建 Venue 和 Organiser 对象
        venue = new Venue();
        venue.setId(1L);
        venue.setSeatsByLevel(new HashMap<>() {{
            put(TicketType.PREMIUM, 100);
        }});

        organiser = new Organiser("test@example.com", "Organizer Name", "password", "Company", "Address", "12345");

        // 创建 Event 对象
        event = new Event();
        event.setId(1L);
        event.setName("Sample Event");
        event.setDescription("Sample Description");
        event.setDate(new Date());
        event.setTime(new Date());
        event.setVenue(venue);
        event.setOrganiser(organiser);
        event.setRemainingSeats(eventDTO.getRemainingSeats());
    }

    // 测试获取所有事件
    @Test
    public void testGetAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(event);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(eventRepository, times(1)).findAll();
    }

    // 测试通过 ID 获取事件
    @Test
    public void testGetEvent() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.getEvent(1L);

        assertNotNull(result);
        assertEquals("Sample Event", result.getName());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetEventNotFound() {
        when(eventRepository.findById(2L)).thenReturn(Optional.empty());

        Event result = eventService.getEvent(2L);

        assertNull(result);
        verify(eventRepository, times(1)).findById(2L);
    }

    // 测试创建事件
    @Test
    public void testCreateEvent() {
        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));
        when(organiserRepository.findById(1L)).thenReturn(Optional.of(organiser));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event createdEvent = eventService.createEvent(eventDTO);

        assertNotNull(createdEvent);
        assertEquals("Sample Event", createdEvent.getName());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testCreateEventVenueNotFound() {
        when(venueRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventService.createEvent(eventDTO);
        });

        assertEquals("Venue not found", exception.getMessage());
    }

    @Test
    public void testCreateEventOrganiserNotFound() {
        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));
        when(organiserRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventService.createEvent(eventDTO);
        });

        assertEquals("Organiser not found", exception.getMessage());
    }

    @Test
    public void testCreateEventSeatingExceedsVenueLimit() {
        eventDTO.setRemainingSeats(new HashMap<>() {{
            put(TicketType.PREMIUM, 200);  // 超过了场地的容量
        }});

        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));
        when(organiserRepository.findById(1L)).thenReturn(Optional.of(organiser));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventService.createEvent(eventDTO);
        });

        assertEquals("Seating capacity exceeds venue limit", exception.getMessage());
    }

    @Test
    public void testUpdateEvent_VenueNotFound() {
        // 模拟场地找不到
        when(venueRepository.findById(1L)).thenReturn(Optional.empty());

        // 验证 RuntimeException 被抛出
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventService.updateEvent(1L, eventDTO);
        });

        assertEquals("Venue not found", exception.getMessage());
    }

    @Test
    public void testUpdateEventNotFound() {
        when(eventRepository.findById(2L)).thenReturn(Optional.empty());

        Event result = eventService.updateEvent(2L, eventDTO);

        assertNull(result);
        verify(eventRepository, times(1)).findById(2L);
    }

    // 测试删除事件
    @Test
    public void testDeleteEvent() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        eventService.deleteEvent(1L);

        verify(eventRepository, times(1)).deleteById(1L);
    }

    // 测试获取可用事件
    @Test
    public void testGetAvailableEvents() {
        List<Event> events = new ArrayList<>();
        events.add(event);
        when(eventRepository.findAvailableEvents()).thenReturn(events);

        List<Event> result = eventService.getAvailableEvents();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(eventRepository, times(1)).findAvailableEvents();
    }

    // 测试场地是否空闲
    @Test
    public void testIsVenueEmptyForDate() {
        Date date = new Date();
        when(eventRepository.findByVenueIdAndDate(1L, date)).thenReturn(new ArrayList<>());

        boolean result = eventService.isVenueEmptyForDate(1L, date);

        assertTrue(result);
        verify(eventRepository, times(1)).findByVenueIdAndDate(1L, date);
    }

    @Test
    public void testIsVenueNotEmptyForDate() {
        Date date = new Date();
        List<Event> events = new ArrayList<>();
        events.add(event);
        when(eventRepository.findByVenueIdAndDate(1L, date)).thenReturn(events);

        boolean result = eventService.isVenueEmptyForDate(1L, date);

        assertFalse(result);
        verify(eventRepository, times(1)).findByVenueIdAndDate(1L, date);
    }
}