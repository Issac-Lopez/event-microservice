package edu.msudenver.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "countries")
public class Country {
    @Column(name = "country_name")
    String countryName;
    @Id
    @Column(name = "country_code")
    String countryCode;

    public Country(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }
}