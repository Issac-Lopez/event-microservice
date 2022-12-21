package edu.msudenver.city;

public class CityRequest {
    private String name;
    private String postalCode;
    private String countryCode;

    public CityRequest(CityRequest cityRequest) {
        this.name = name;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }
    public CityRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
