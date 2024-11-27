package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.dto.EventDTO;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.repos.EventRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;
import ucd.comp3013j.ems.model.repos.VenueRepository;

import java.util.ArrayList;
import java.util.List;
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
        event.setDate(dto.getDate());
        event.setTime(dto.getTime());
        
        // 设置场地
        if (dto.getVenueId() != null) {
            Venue venue = venueRepository.findById(dto.getVenueId()).orElse(null);
            event.setVenue(venue);
        }
        
        // 设置组织者
        if (dto.getOrganiserId() != null) {
            Organiser organiser = organiserRepository.findById(dto.getOrganiserId()).orElse(null);
            event.setOrganiser(organiser);
        }
        
        // 设置票价和座位数量
        event.setPricesByLevel(dto.getPricesByLevel());
        event.setRemainingSeats(dto.getRemainingSeats());
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
}
