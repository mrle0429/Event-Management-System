package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.dto.VenueDTO;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.repos.VenueRepository;

import java.util.List;

/**
 * Venue Management Service
 * Handles all business logic related to venues, including:
 * - Venue creation and modification
 * - Venue queries and searches
 * - Venue capacity management
 * - Venue information management
 */
@Service
public class VenueService {

    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    /**
     * Creates a new venue.
     * Initializes venue with basic information and seating configuration.
     *
     * @param venueDTO DTO containing venue information
     */
    public void createVenue(VenueDTO venueDTO) {
        if(venueRepository.findByName(venueDTO.getName()) != null){
            throw new RuntimeException("Venue Name already exists");
        }
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

    /**
     * Retrieves all venues in the system.
     *
     * @return List of all venues
     */
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    /**
     * Retrieves a specific venue by its ID.
     *
     * @param id Venue ID to search for
     * @return The found Venue object, or null if not found
     */
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    /**
     * Updates an existing venue's information.
     * Validates venue existence before update.
     *
     * @param venueDTO DTO containing updated venue information
     * @throws RuntimeException if venue not found
     */
    public void updateVenue(VenueDTO venueDTO) {
        Venue venue = venueRepository.findById(venueDTO.getId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        if (!venue.getName().equals(venueDTO.getName())) {
                    Venue venueWithSameName = venueRepository.findByName(venueDTO.getName());
                    if (venueWithSameName != null && !venueWithSameName.getId().equals(venueDTO.getId())) {
                        throw new RuntimeException("Venue name already exists");
                    }
                }

        updateVenueFromDTO(venue, venueDTO);
        venueRepository.save(venue);
    }

    /**
     * Deletes a venue by its ID.
     *
     * @param id Venue ID to delete
     */
    public void deleteVenue(Long id) {
        venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        venueRepository.deleteById(id);
    }

    /**
     * Updates venue information from DTO.
     * Helper method for create and update operations.
     *
     * @param venue Venue object to update
     * @param dto DTO containing new venue information
     */
    private void updateVenueFromDTO(Venue venue, VenueDTO dto) {
        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setDescription(dto.getDescription());
        venue.setContactName(dto.getContactName());
        venue.setContactPhone(dto.getContactPhone());
        venue.setContactEmail(dto.getContactEmail());
        venue.setSeatsByLevel(dto.getSeatsByLevel());
    }

    public boolean existsByName(String name) {
        return venueRepository.findByName(name) != null;
    }
}
