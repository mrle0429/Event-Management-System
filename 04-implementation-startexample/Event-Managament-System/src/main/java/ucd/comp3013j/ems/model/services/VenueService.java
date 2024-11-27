package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.entities.Venue;
import ucd.comp3013j.ems.model.repos.VenueRepository;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    // 获取所有场馆
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    // 获取特定场馆
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElse(null);
    }

    // 创建场馆
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }
}
