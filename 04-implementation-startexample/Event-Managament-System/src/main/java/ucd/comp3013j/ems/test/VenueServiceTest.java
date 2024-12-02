package ucd.comp3013j.ems.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.repos.VenueRepository;

import java.util.*;

import ucd.comp3013j.ems.model.services.VenueService;

public class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @InjectMocks
    private VenueService venueService;

    private VenueDTO venueDTO;
    private Venue venue;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 创建 VenueDTO 对象
        venueDTO = new VenueDTO();
        venueDTO.setId(1L);
        venueDTO.setName("Sample Venue");
        venueDTO.setAddress("123 Sample Street");
        venueDTO.setDescription("Sample Description");
        venueDTO.setContactPhone("123-456-7890");
        venueDTO.setContactEmail("contact@sample.com");
        venueDTO.setContactName("John Doe");

        // 创建一个 Map 来表示票种与座位数
        Map<TicketType, Integer> seatsByLevel = new HashMap<>();
        seatsByLevel.put(TicketType.PREMIUM, 100);  // 设置 100 张常规票
        venueDTO.setSeatsByLevel(seatsByLevel);

        // 创建 Venue 对象
        venue = new Venue();
        venue.setId(1L);
        venue.setName("Sample Venue");
        venue.setAddress("123 Sample Street");
        venue.setDescription("Sample Description");
        venue.setContactPhone("123-456-7890");
        venue.setContactEmail("contact@sample.com");
        venue.setContactName("John Doe");
        venue.setSeatsByLevel(seatsByLevel);
    }

    // 测试创建场地
    @Test
    public void testCreateVenue() {
        when(venueRepository.save(any(Venue.class))).thenReturn(venue);

        venueService.createVenue(venueDTO);

        verify(venueRepository, times(1)).save(any(Venue.class));
    }

    // 测试获取所有场地
    @Test
    public void testGetAllVenues() {
        List<Venue> venues = new ArrayList<>();
        venues.add(venue);
        when(venueRepository.findAll()).thenReturn(venues);

        List<Venue> result = venueService.getAllVenues();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sample Venue", result.get(0).getName());
        verify(venueRepository, times(1)).findAll();
    }

    // 测试根据 ID 获取场地
    @Test
    public void testGetVenueById() {
        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));

        Venue result = venueService.getVenueById(1L);

        assertNotNull(result);
        assertEquals("Sample Venue", result.getName());
        verify(venueRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetVenueById_NotFound() {
        when(venueRepository.findById(1L)).thenReturn(Optional.empty());

        Venue result = venueService.getVenueById(1L);

        assertNull(result);
        verify(venueRepository, times(1)).findById(1L);
    }

    // 测试更新场地
    @Test
    public void testUpdateVenue() {
        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));
        when(venueRepository.save(any(Venue.class))).thenReturn(venue);

        venueDTO.setName("Updated Venue");
        venueService.updateVenue(venueDTO);

        assertEquals("Updated Venue", venue.getName());
        verify(venueRepository, times(1)).save(any(Venue.class));
    }

    @Test
    public void testUpdateVenue_NotFound() {
        when(venueRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            venueService.updateVenue(venueDTO);
        });

        assertEquals("Venue not found", exception.getMessage());
        verify(venueRepository, times(1)).findById(1L);
    }

    // 测试删除场地
    @Test
    public void testDeleteVenue() {
        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));

        venueService.deleteVenue(1L);

        verify(venueRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteVenue_NotFound() {
        when(venueRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            venueService.deleteVenue(1L);
        });

        assertEquals("Venue not found", exception.getMessage());
        verify(venueRepository, times(1)).findById(1L);
    }
}

