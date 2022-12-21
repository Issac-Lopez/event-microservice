package edu.msudenver.city;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.msudenver.country.Country;
import javax.persistence.*;
// lombok is a library that generates getters and setters for you
// https://projectlombok.org/
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "cities")
@IdClass(CityId.class)
public class City {
    @Id
    @Column(name = "postal_code")
    private String postalCode;

    @Id
    @ManyToOne()
    @JoinColumn(name = "country_code", referencedColumnName = "country_code", insertable = false, updatable = false)
    private Country country;

    @Column(name = "country_code")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // serializer
    private String countryCode;

    @Column(name = "name")
    private String name;

    public City(String postalCode, Country country, String countryCode, String name) {
        this.postalCode = postalCode;
        this.country = country;
        this.countryCode = countryCode;
        this.name = name;
    }
}