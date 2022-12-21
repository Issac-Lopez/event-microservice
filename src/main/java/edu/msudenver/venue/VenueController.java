package edu.msudenver.venue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {
    @Autowired
    private VenueService venueService;

    // get a list of all venues
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Venue>> getVenues() {
        return ResponseEntity.ok(venueService.getVenues());
    }
    // get a single venue
    @GetMapping(path = "/{venueId}", produces = "application/json")
    public ResponseEntity<Venue> getVenue(@PathVariable Long venueId) {
        Venue venue = venueService.getVenue(venueId);
        try {
            return new ResponseEntity<>(venue, venue == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity createVenue(@RequestBody Venue venue) {
        try {
            return new ResponseEntity<>(venueService.saveVenue(venue), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{venueId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Venue> updateVenue(@PathVariable Long venueId,
                                             @RequestBody Venue updatedVenue) {
        Venue retrievedVenue = venueService.getVenue(venueId);
        if (retrievedVenue != null) {
            retrievedVenue.setName(updatedVenue.getName());
            retrievedVenue.setCity(updatedVenue.getCity());
            retrievedVenue.setStreetAddress(updatedVenue.getStreetAddress());
            retrievedVenue.setType(updatedVenue.getType());
            retrievedVenue.setActive(updatedVenue.getActive());
            try {
                return ResponseEntity.ok(venueService.saveVenue(retrievedVenue));
            } catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{venueId}")
    public ResponseEntity<Venue> deleteVenue(@PathVariable Long venueId) {
        Venue retrievedVenue = venueService.getVenue(venueId);
        if (retrievedVenue != null) {
            venueService.deleteVenue(venueId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
