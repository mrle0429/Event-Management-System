package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.*;

/**
 * Event Management Service
 * Handles all business logic related to events, including:
 * - Event creation and modification
 * - Event queries and searches
 * - Event capacity management
 * - Event-venue relationship management
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private OrganiserRepository organiserRepository;

    /**
     * Retrieves all events in the system.
     * 
     * @return List of all events
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Retrieves a specific event by its ID.
     *
     * @param id Event ID to search for
     * @return The found Event object, or null if not found
     */
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    /**
     * Creates a new event.
     * Validates venue capacity and organiser existence before creation.
     *
     * @param eventDTO DTO containing event information
     * @return The created Event object
     * @throws RuntimeException if venue not found, organiser not found, or seating capacity exceeds venue limit
     */
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
        event.setRemainingSeats(eventDTO.getRemainingSeats());
        return eventRepository.save(event);
    }

    /**
     * Updates an existing event.
     *
     * @param id Event ID to update
     * @param eventDTO DTO containing updated event information
     * @return The updated Event object, or null if event not found
     */
    public Event updateEvent(Long id, EventDTO eventDTO) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            updateEventFromDTO(event, eventDTO);
            return eventRepository.save(event);
        }
        return null;
    }

    /**
     * Deletes an event by its ID.
     *
     * @param id Event ID to delete
     */
    public void deleteEvent(Long id) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    /**
     * Updates event information from DTO.
     * Helper method for create and update operations.
     *
     * @param event Event object to update
     * @param dto DTO containing new event information
     * @throws RuntimeException if date/time parsing fails, venue not found, or organiser not found
     */
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
    }

    /**
     * Retrieves all events that have remaining seats available.
     *
     * @return List of available events
     */
    public List<Event> getAvailableEvents() {
        List<Event> events = eventRepository.findAvailableEvents();
        if (events == null || events.isEmpty()) {
            return new ArrayList<>();
        }
        return events;
    }

    /**
     * Checks if a venue is available for a specific date.
     *
     * @param venueId Venue ID to check
     * @param date Date to check availability for
     * @return true if venue is available, false otherwise
     */
    public boolean isVenueEmptyForDate(Long venueId, Date date) {
        return eventRepository.findByVenueIdAndDate(venueId, date).isEmpty();
    }

    /**
     * Validates if the requested seating capacity is within venue limits.
     *
     * @param venue Venue to check capacity against
     * @param eventSeats Map of ticket types to requested seat counts
     * @return true if capacity is valid, false otherwise
     */
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

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Organiser getOrganiserByEmail(String email) {
        return organiserRepository.findByEmail(email);
    }

    public List<Organiser> getAllOrganisers() {
        return organiserRepository.findAll();
    }

}
