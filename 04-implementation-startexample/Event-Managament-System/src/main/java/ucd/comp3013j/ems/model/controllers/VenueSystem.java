package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.services.VenueService;

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
        model.addAttribute("venue", venueService.getVenueById(id));
        return "venue/detail";
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
        model.addAttribute("venueDTO", venueService.getVenueById(id));
        return "venue/edit";
    }

    /**
     * Updates an existing venue.
     * Accessible by: Administrators
     * @param id Venue ID
     * @param venueDTO Venue data transfer object
     * @return Redirects to venues list page
     */
    @PostMapping("/{id}/edit")
    public String updateVenue(@PathVariable Long id, @ModelAttribute VenueDTO venueDTO) {
        venueService.updateVenue(venueDTO);
        return "redirect:/venue";
    }

    /**
     * Deletes a venue.
     * Accessible by: Administrators
     * @param id Venue ID to delete
     * @return Redirects to venues list page
     */
    @PostMapping("/{id}/delete")
    public String deleteVenue(@PathVariable Long id) {
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
        return "venue/create";
    }

    /**
     * Creates a new venue.
     * Accessible by: Administrators
     * @param venueDTO Venue data transfer object
     * @return Redirects to venues list page
     */
    @PostMapping("/create")
    public String createVenue(@ModelAttribute VenueDTO venueDTO, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Venue Create Success!");
        redirectAttributes.addFlashAttribute("messageType", "success");
        venueService.createVenue(venueDTO);
        return "redirect:/venue";
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
        return "venue/list";
    }
} 