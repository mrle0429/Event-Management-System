package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ucd.comp3013j.ems.model.dto.EventDTO;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.repos.EventRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;
import ucd.comp3013j.ems.model.repos.VenueRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private VenueRepository venueRepository;
    
    @Autowired
    private OrganiserRepository organiserRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(EventDTO eventDTO) {
        Venue venue = venueRepository.findById(eventDTO.getVenueId())
            .orElseThrow(() -> new RuntimeException("Venue not found"));
            
        Organiser organiser = organiserRepository.findById(eventDTO.getOrganiserId())
            .orElseThrow(() -> new RuntimeException("Organiser not found"));
            
        if (!isValidSeatingCapacity(venue, eventDTO.getRemainingSeats())) {
            throw new RuntimeException("Seating capacity exceeds venue limit");
        }
        
        Event event = new Event();
        updateEventFromDTO(event, eventDTO);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, EventDTO eventDTO) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            updateEventFromDTO(event, eventDTO);
            return eventRepository.save(event);
        }
        return null;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private void updateEventFromDTO(Event event, EventDTO dto) {
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            
            String dateStr = dateFormat.format(dto.getDate());
            String timeStr = timeFormat.format(dto.getTime());
            
            event.setDate(dateFormat.parse(dateStr));
            event.setTime(timeFormat.parse(timeStr));
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date/time", e);
        }
        
        Venue venue = venueRepository.findById(dto.getVenueId())
            .orElseThrow(() -> new RuntimeException("Venue not found"));
        event.setVenue(venue);
        
        Organiser organiser = organiserRepository.findById(dto.getOrganiserId())
            .orElseThrow(() -> new RuntimeException("Organiser not found"));
        event.setOrganiser(organiser);
        
        event.setPricesByLevel(dto.getPricesByLevel());
        //event.setRemainingSeats(dto.getRemainingSeats());
    }

    public List<Event> getAvailableEvents() {
        List<Event> events = eventRepository.findAvailableEvents();
        
        if (events == null || events.isEmpty()) {
            System.out.println("没有找到可用的活动");
            return new ArrayList<>();
        }
        
        System.out.println("找到 " + events.size() + " 个可用活动：");
        /*events.forEach(event -> {
            System.out.println("活动ID: " + event.getId());
            System.out.println("活动名称: " + event.getName());
            System.out.println("活动日期: " + event.getDate());
            System.out.println("活动地点: " + event.getVenue().getName());
            System.out.println("------------------------");
        });

         */
        
        return events;
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Organiser getOrganiserByEmail(String email) {
        return organiserRepository.findByEmail(email);
    }

    private boolean isValidSeatingCapacity(Venue venue, Map<TicketType, Integer> eventSeats) {
        if (venue.getSeatsByLevel() == null || eventSeats == null) {
            return false;
        }
        
        return eventSeats.entrySet().stream()
            .allMatch(entry -> {
                Integer venueCapacity = venue.getSeatsByLevel().get(entry.getKey());
                return venueCapacity != null && entry.getValue() <= venueCapacity;
            });
    }

    public List<Organiser> getAllOrganisers() {
        return organiserRepository.findAll();
    }

}
