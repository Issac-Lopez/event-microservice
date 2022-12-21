package edu.msudenver.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.city.City;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
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
}