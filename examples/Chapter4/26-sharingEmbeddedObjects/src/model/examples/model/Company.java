package examples.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Company {
    @Id private int id;
    
    @Embedded
    private Address address;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Address getAddress() {
        return this.address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        return "Company id: " + getId() + " address: " + getAddress();

    }
    
}