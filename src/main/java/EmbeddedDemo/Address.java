
package EmbeddedDemo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String pincode;

    public Address() {}

    public Address(String street, String city, String pincode) {
        this.street = street;
        this.city = city;
        this.pincode = pincode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }
}
