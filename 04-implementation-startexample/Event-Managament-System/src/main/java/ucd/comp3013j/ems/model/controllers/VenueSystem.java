package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.services.VenueService;

@Controller
@RequestMapping("/venue")
public class VenueSystem {
    
    private final VenueService venueService;
    
    @Autowired
    public VenueSystem(VenueService venueService) {
        this.venueService = venueService;
    }
    
    @GetMapping("/{id}")
    public String showVenueDetails(@PathVariable Long id, Model model) {
        model.addAttribute("venue", venueService.getVenueById(id));
        return "venue/detail";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("venueDTO", venueService.getVenueById(id));
        return "venue/edit";
    }
    
    @PostMapping("/{id}/edit")
    public String updateVenue(@PathVariable Long id, @ModelAttribute VenueDTO venueDTO) {
        venueService.updateVenue(venueDTO);
        return "redirect:/venue";
    }
    
    @PostMapping("/{id}/delete")
    public String deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return "redirect:/venue";
    }
    
    @GetMapping("/create")
    public String showCreateVenueForm(Model model) {
        model.addAttribute("venueDTO", new VenueDTO());
        return "venue/create";
    }
    
    @PostMapping("/create")
    public String createVenue(@ModelAttribute VenueDTO venueDTO) {
        venueService.createVenue(venueDTO);
        return "redirect:/venue";
    }
    
    @GetMapping
    public String listVenues(Model model) {
        
        model.addAttribute("venues", venueService.getAllVenues());
        return "venue/list";
    }
} 