package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.services.VenueService;

import java.util.Collections;
import java.util.Map;

/**
 * Controller for handling venue-related operations.
 * This controller manages venue creation, updates, deletions, and viewing.
 */
@Controller
@RequestMapping("/venue")
public class VenueSystem {

    private final VenueService venueService;

    @Autowired
    public VenueSystem(VenueService venueService) {
        this.venueService = venueService;
    }

    /**
     * Displays detailed information for a specific venue.
     * Accessible by: All users
     * @param id Venue ID
     * @param model Spring MVC Model object
     * @return The venue detail page view
     */
    @GetMapping("/{id}")
    public String showVenueDetails(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("venue", venueService.getVenueById(id));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "venue/detail-venue";
    }

    /**
     * Displays the venue edit form.
     * Accessible by: Administrators
     * @param id Venue ID
     * @param model Spring MVC Model object
     * @return The venue edit page view
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("venueDTO", venueService.getVenueById(id));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "venue/edit-venue";
    }

    /**
     * Updates an existing venue.
     * Accessible by: Administrators
     * @param id Venue ID
     * @param venueDTO Venue data transfer object
     * @return Redirects to venues list page
     */
    @PostMapping("/{id}/edit")
    public String updateVenue(@PathVariable Long id, @ModelAttribute VenueDTO venueDTO, RedirectAttributes redirectAttributes) {
        try{
            venueService.updateVenue(venueDTO);
            redirectAttributes.addFlashAttribute("message", "Venue Update Success!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/venue/" + id ;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/venue/" + id + "/edit";
        }
    }

    /**
     * Deletes a venue.
     * Accessible by: Administrators
     * @param id Venue ID to delete
     * @return Redirects to venues list page
     */
    @PostMapping("/{id}/delete")
    public String deleteVenue(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Venue Deleted Success!");
        redirectAttributes.addFlashAttribute("messageType", "success");
        venueService.deleteVenue(id);
        return "redirect:/venue";
    }

    /**
     * Displays the venue creation form.
     * Accessible by: Administrators
     * @param model Spring MVC Model object
     * @return The venue creation page view
     */
    @GetMapping("/create")
    public String showCreateVenueForm(Model model) {
        model.addAttribute("venueDTO", new VenueDTO());
        return "venue/create-venue";
    }

    /**
     * Creates a new venue.
     * Accessible by: Administrators
     * @param venueDTO Venue data transfer object
     * @return Redirects to venues list page
     */
    @PostMapping("/create")
    public String createVenue(@ModelAttribute VenueDTO venueDTO, RedirectAttributes redirectAttributes) {
        try {
            venueService.createVenue(venueDTO);
            redirectAttributes.addFlashAttribute("message", "Venue created successfully");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/venue";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
            redirectAttributes.addFlashAttribute("venueDTO", venueDTO);
            return "redirect:/venue/create";
        }
    }

    /**
     * Lists all venues in the system.
     * Accessible by: All users
     * @param model Spring MVC Model object
     * @return The venues list page view
     */
    @GetMapping
    public String listVenues(Model model) {
        model.addAttribute("venues", venueService.getAllVenues());
        return "venue/list-venues";
    }

    @GetMapping("/check-name")
    @ResponseBody
    public Map<String, Boolean> checkVenueName(@RequestParam String name) {
        boolean exists = venueService.existsByName(name);
        return Collections.singletonMap("exists", exists);
    }
} 