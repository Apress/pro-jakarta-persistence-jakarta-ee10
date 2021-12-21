package examples.model;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;


@Entity
public class Customer {
    @Id
    int id;
    
    @Embedded
    @AttributeOverride(name = "residence.zip", column = @Column(name = "ZIP"))
    @AssociationOverride(name = "primaryPhone", joinColumns = @JoinColumn(name = "EMERG_PHONE"))
    @AssociationOverride(name = "phones", joinTable = @JoinTable(name = "CUST_PHONE"))
    private ContactInfo contactInfo;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String toString() {
        return "Customer id: " + getId() + " contactInfo: " + getContactInfo();

    }
    
}