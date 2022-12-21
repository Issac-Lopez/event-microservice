package edu.msudenver.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    // get a list of all venues
    public List<Venue> getVenues() {
        return venueRepository.findAll();
    }

    @Transactional
    public Venue saveVenue(Venue venue) {
        venue = venueRepository.saveAndFlush(venue);
        entityManager.refresh(venue);
        return venue;
    }

    public boolean deleteVenue(Long venueId) {
        try {
            if (venueRepository.existsById(venueId)) {
                venueRepository.deleteById(venueId);
                return true;
            }
        } catch (IllegalArgumentException var3) {
        }
        return false;
    }

    public Venue getVenue(Long venueId) {
        try {
            return venueRepository.findById(venueId).get();
        } catch (IllegalArgumentException | NoSuchElementException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}