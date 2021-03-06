package examples.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceUnitUtil;

@Entity
public class Address {
    @Id
    private int id;
    private String street;
    private String city;
    private String state;
    private String zip;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String address) {
        this.street = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String toString() {
        return "Address id: " + getId() + 
               ", street: " + getStreet() +
               ", city: " + getCity() +
               ", state: " + getState() +
               ", zip: " + getZip();
    }

    public String toLoadedString(PersistenceUnitUtil util) {
        return "Address id: " + getId() + 
               ": street: " + (util.isLoaded(this, "street") ? getStreet() : "N/L") +
               ", city: " + (util.isLoaded(this, "city") ? getCity() : "N/L") +
               ", state: " + (util.isLoaded(this, "state") ? getState() : "N/L") +
               ", zip: " + (util.isLoaded(this, "zip") ? getZip() : "N/L");
    }
}
