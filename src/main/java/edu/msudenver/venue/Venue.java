package edu.msudenver.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.city.City;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "venues")
public class Venue implements Serializable {
    @Id
    @Column(name = "venue_id", columnDefinition = "SERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;
    @Column(name = "name")
    private String name;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "country_code", referencedColumnName = "country_code", insertable = false, updatable = false),
            @JoinColumn(name = "postal_code", referencedColumnName = "postal_code", insertable = false, updatable = false)
    })
    private City city; // composite foregin key

    @Column(name = "postal_code")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String postalCode;
    @Column(name = "country_code")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String countryCode;

    @Column(name = "active")
    private boolean active;

    public Venue(Long venueId, String name, String streetAddress, String type, City city, String postalCode, boolean active) {
        this.venueId = venueId;
        this.name = name;
        this.streetAddress = streetAddress;
        this.type = type;
        this.city = city;
        this.postalCode = postalCode;
        this.active = active;
    }

    public Venue() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}