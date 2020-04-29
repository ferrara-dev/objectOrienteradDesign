package model.physicalobjects;

/**
 * Utility class representing a general address of a building
 */
public class Address {
    private String street;
    private String city;
    private String country;
    private String postalCode;

    public Address(String street, String city, String country, String postalCode){
        this.city = city;
        this.street = street;
        this.country = country;
        this.postalCode = postalCode;
    }

    /**
     * method used to change address
     * @param street
     * @param city
     * @param country
     * @param postalCode
     * @return returns the new address
     */
    public Address changeAddress(String street, String city, String country, String postalCode){
        return new Address(street,city,country,postalCode);
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Address : " +
                "street='" + street + '\n' +
                "city='" + city + '\n' +
                "country='" + country + '\n' +
                "postalCode='" + postalCode + '\n';
    }
}
