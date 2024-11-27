package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucd.comp3013j.ems.model.dto.EventDTO;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.services.EventService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventSystem {
    
    @Autowired
    private EventService eventService;

    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "event/list";
    }

    @GetMapping("/{id}")
    public String viewEvent(@PathVariable Long id, Model model) {
        Event event = eventService.getEvent(id);
        model.addAttribute("event", event);
        return "event/view";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("eventDTO", new EventDTO());
        return "event/create";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventDTO eventDTO, Authentication authentication) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            // 设置组织者ID
            // TODO: 根据实际需求设置organiserId
        }
        eventService.createEvent(eventDTO);
        return "redirect:/events";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEvent(id);
        // TODO: 将Event转换为EventDTO
        model.addAttribute("eventDTO", event);
        return "event/edit";
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
}
