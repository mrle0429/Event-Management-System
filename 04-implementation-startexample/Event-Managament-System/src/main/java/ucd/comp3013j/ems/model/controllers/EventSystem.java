package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucd.comp3013j.ems.model.dto.EventDTO;
import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.services.EventService;
import ucd.comp3013j.ems.model.services.VenueService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;
import ucd.comp3013j.ems.model.services.VenueService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventSystem {
    
    @Autowired
    private EventService eventService;

    @Autowired
    private VenueService venueService;

    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/list";
    }

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
                    Event event = eventService.createEvent(eventDTO);
                    return "redirect:/administrator";
                } else {
                    Organiser organiser = eventService.getOrganiserByEmail(userEmail);
                    eventDTO.setOrganiserId(organiser.getId());
                    Event event = eventService.createEvent(eventDTO);
                    return "redirect:/events/organiser";
                }
            }

            throw new RuntimeException("Invalid authentication");
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date or time format", e);
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEvent(id);
        // TODO: 将Event转换为EventDTO
        model.addAttribute("eventDTO", event);
        return "events/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateEvent(@PathVariable Long id, @ModelAttribute EventDTO eventDTO) {
        eventService.updateEvent(id, eventDTO);
        return "redirect:/events";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/available")
    public String listAvailableEvents(Model model) {
        List<Event> availableEvents = eventService.getAvailableEvents();
        model.addAttribute("events", availableEvents);
        return "events/available";
    }

    // venue 部分 ----------------------------------------------------------------

    // 显示所有场馆
    @GetMapping("/venues")
    public String listVenues(Model model) {
        // 获取所有场馆
        List<Venue> venues = venueService.getAllVenues();
        model.addAttribute("venues", venues);
        return "venue/list";
    }

    // 显示创建场馆表单
    @GetMapping("/venues/create")
    public String showCreateVenueForm(Model model) {
        model.addAttribute("venue", new VenueDTO());
        return "venue/create";
    }

    // 处理创建场馆的请求
    @PostMapping("/venues/create")
    public String createVenue(@ModelAttribute VenueDTO venueDTO, Authentication authentication) {
        if (authentication.getPrincipal() instanceof AccountWrapper accountWrapper) {
            String role = accountWrapper.getAuthorities().iterator().next().getAuthority();
            // 只有ORGANISER可以创建
            if ("ORGANISER".equals(role)) {
                Venue venue = new Venue();
                venue.setName(venueDTO.getName());
                venue.setAddress(venueDTO.getAddress());
                venue.setContactName(venueDTO.getContactName());
                venue.setContactPhone(venueDTO.getContactPhone());
                venue.setContactEmail(venueDTO.getContactEmail());
                venue.setDescription(venueDTO.getDescription());
                venue.setSeatsByLevel(venueDTO.getSeatsByLevel());

                venueService.createVenue(venue);
            }
        }
        // 返回场馆列表
        return "redirect:/events/venues";
    }

}
