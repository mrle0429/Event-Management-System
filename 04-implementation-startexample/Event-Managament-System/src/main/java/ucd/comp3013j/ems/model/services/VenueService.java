package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.repos.VenueRepository;

import java.util.List;

@Service
public class VenueService {
    
    private final VenueRepository venueRepository;
    
    @Autowired
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }
    
    public void createVenue(VenueDTO venueDTO) {
        Venue venue = new Venue();
        venue.setName(venueDTO.getName());
        venue.setAddress(venueDTO.getAddress());
        venue.setDescription(venueDTO.getDescription());
        venue.setContactPhone(venueDTO.getContactPhone());
        venue.setContactEmail(venueDTO.getContactEmail());
        venue.setContactName(venueDTO.getContactName());
        venue.setSeatsByLevel(venueDTO.getSeatsByLevel());
        
        venueRepository.save(venue);
    }
    
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }
    
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElse(null);
    }
    
    public void updateVenue(VenueDTO venueDTO) {
        Venue venue = venueRepository.findById(venueDTO.getId())
            .orElseThrow(() -> new RuntimeException("Venue not found"));
            
        updateVenueFromDTO(venue, venueDTO);
        venueRepository.save(venue);
    }
    
    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
    
    private void updateVenueFromDTO(Venue venue, VenueDTO dto) {
        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setDescription(dto.getDescription());
        venue.setContactName(dto.getContactName());
        venue.setContactPhone(dto.getContactPhone());
        venue.setContactEmail(dto.getContactEmail());
        venue.setSeatsByLevel(dto.getSeatsByLevel());
    }
}
