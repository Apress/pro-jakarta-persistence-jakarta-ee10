package examples.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Phone {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="NUM")
    private String number;
    private String type;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String phoneNo) {
        this.number = phoneNo;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String phoneType) {
        this.type = phoneType;
    }

    public String toString() {
        return "Phone id: " + getId() + 
               ", no: " + getNumber() +
               ", type: " + getType();
    }
}
