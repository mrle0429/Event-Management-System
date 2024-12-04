package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucd.comp3013j.ems.model.dto.EventDTO;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.services.EventService;
import ucd.comp3013j.ems.model.services.VenueService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventSystem {

    @Autowired
    private EventService eventService;

    @Autowired
    private VenueService venueService;

    /**
     * Lists all events in the system.
     * Accessible by: All users
     *
     * @param model Spring MVC Model object
     * @return The events list page view
     */
    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/list";
    }

    /**
     * Displays detailed information for a specific event.
     * Accessible by: All users
     *
     * @param id    Event ID
     * @param model Spring MVC Model object
     * @return The event detail page view
     */
    @GetMapping("/{id}")
    public String showEventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        System.out.println("Fetching event with ID: " + id);
        if (event != null) {
            System.out.println("Found event: " + event.getName());
        } else {
            System.out.println("Event not found");
        }

        model.addAttribute("event", event);
        return "events/detail";
    }

    /**
     * Displays the event creation form.
     * Accessible by: Administrators and Organisers
     *
     * @param model          Spring MVC Model object
     * @param authentication Current user's authentication information
     * @return The event creation page view or redirects to login/home page
     */
    @GetMapping("/create")
    public String showCreateForm(Model model, Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof AccountWrapper)) {
            return "redirect:/login";
        }

        AccountWrapper aw = (AccountWrapper) authentication.getPrincipal();
        if (!aw.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR") ||
                        a.getAuthority().equals("ORGANISER"))) {
            return "redirect:/";
        }

        model.addAttribute("eventDTO", new EventDTO());
        model.addAttribute("venues", venueService.getAllVenues());
        if (aw.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
            model.addAttribute("organisers", eventService.getAllOrganisers());
        }
        return "events/create";
    }

    /**
     * Handles the creation of a new event.
     * Accessible by: Administrators and Organisers
     *
     * @param eventDTO       Event data transfer object
     * @param dateStr        Event date string in yyyy-MM-dd format
     * @param timeStr        Event time string in HH:mm format
     * @param authentication Current user's authentication information
     * @return Redirects to administrator or organiser page based on user role
     * @throws RuntimeException if date/time format is invalid or administrator doesn't specify organiser
     */
    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventDTO eventDTO,
                              @RequestParam("dateStr") String dateStr,
                              @RequestParam("timeStr") String timeStr,
                              Authentication authentication) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            eventDTO.setDate(dateFormat.parse(dateStr));
            eventDTO.setTime(timeFormat.parse(timeStr));

            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                String userEmail = aw.getUsername();
                if (aw.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                    if (eventDTO.getOrganiserId() == null) {
                        throw new RuntimeException("Administrator must specify an organiser");
                    }
                    eventService.createEvent(eventDTO);
                    return "redirect:/administrator";
                } else {
                    Organiser organiser = eventService.getOrganiserByEmail(userEmail);
                    eventDTO.setOrganiserId(organiser.getId());
                    eventService.createEvent(eventDTO);
                    return "redirect:/organiser";
                }
            }

            throw new RuntimeException("Invalid authentication");
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date or time format", e);
        }
    }

    /**
     * Checks if a venue is available for a specific date.
     * Accessible by: Administrators and Organisers
     *
     * @param venueId Venue ID to check
     * @param date    Date to check availability for
     * @return ResponseEntity containing boolean indicating venue availability
     */
    @GetMapping("checkVenue")
    public ResponseEntity<Boolean> checkVenue(@RequestParam Long venueId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(eventService.isVenueEmptyForDate(venueId, date));
    }

    @GetMapping("checkVenueCapacity")
    public ResponseEntity<Boolean> checkVenueCapacity(
            @RequestParam Long venueId,
            @RequestParam("type") String ticketType,
            @RequestParam Integer requestedSeats) {
        Venue venue = venueService.getVenueById(venueId);
        if (venue == null) {
            return ResponseEntity.ok(false);
        }

        Integer capacity = venue.getSeatsByLevel().get(TicketType.valueOf(ticketType));
        return ResponseEntity.ok(capacity >= requestedSeats);
    }

    /**
     * Displays the event edit form.
     * Accessible by: Administrators and event owners (Organisers)
     *
     * @param id             Event ID
     * @param model          Spring MVC Model object
     * @param authentication Current user's authentication information
     * @return The event edit page view
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, Authentication authentication) {
        try {
            Event event = eventService.getEvent(id);

            // 将 Event 转换为 EventDTO
            EventDTO eventDTO = new EventDTO();
            eventDTO.setName(event.getName());
            eventDTO.setDescription(event.getDescription());
            eventDTO.setDate(event.getDate());
            eventDTO.setTime(event.getTime());
            eventDTO.setVenueId(event.getVenue().getId());
            eventDTO.setOrganiserId(event.getOrganiser().getId());
            eventDTO.setPricesByLevel(event.getPricesByLevel());
            eventDTO.setRemainingSeats(event.getRemainingSeats());

            model.addAttribute("event", event);
            model.addAttribute("eventDTO", eventDTO);
            model.addAttribute("venues", venueService.getAllVenues());

            AccountWrapper aw = (AccountWrapper) authentication.getPrincipal();
            if (aw.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                model.addAttribute("organisers", eventService.getAllOrganisers());
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "events/edit";
    }

    /**
     * Updates an existing event.
     * Accessible by: Administrators and event owners (Organisers)
     *
     * @param id             Event ID
     * @param eventDTO       Event data transfer object
     * @param dateStr        Event date string in yyyy-MM-dd format
     * @param timeStr        Event time string in HH:mm format
     * @param authentication Current user's authentication information
     * @return Redirects to administrator or organiser page based on user role
     * @throws RuntimeException if date/time format is invalid or administrator doesn't specify organiser
     */
    @PostMapping("/{id}/edit")
    public String updateEvent(@PathVariable Long id, @ModelAttribute EventDTO eventDTO,
                              @RequestParam("dateStr") String dateStr,
                              @RequestParam("timeStr") String timeStr,
                              Authentication authentication) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            eventDTO.setDate(dateFormat.parse(dateStr));
            eventDTO.setTime(timeFormat.parse(timeStr));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date or time format", e);
        }

        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            String userEmail = aw.getUsername();
            if (aw.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                if (eventDTO.getOrganiserId() == null) {
                    throw new RuntimeException("Administrator must specify an organiser");
                }
                eventService.updateEvent(id, eventDTO);
                return "redirect:/administrator";
            } else {
                Organiser organiser = eventService.getOrganiserByEmail(userEmail);
                eventDTO.setOrganiserId(organiser.getId());
                eventService.updateEvent(id, eventDTO);
                return "redirect:/organiser";
            }
        }
        return "redirect:/events";
    }

    /**
     * Deletes an event.
     * Accessible by: Administrators and event owners (Organisers)
     *
     * @param id Event ID to delete
     * @return Redirects to events list page
     */
    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    /**
     * Lists all available events (events with remaining seats).
     * Accessible by: All users
     *
     * @param model Spring MVC Model object
     * @return The available events list page view
     */
    @GetMapping("/available")
    public String listAvailableEvents(Model model) {
        List<Event> availableEvents = eventService.getAvailableEvents();
        model.addAttribute("events", availableEvents);
        return "events/available";
    }

}
