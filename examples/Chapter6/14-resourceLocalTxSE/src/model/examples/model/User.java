package examples.model;

import java.text.DateFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="USER_DB")
public class User {
    @Id
    private String name;
    private String password;
    @Temporal(TemporalType.DATE)
    private java.util.Calendar lastChange;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String name) {
        this.password = name;
    }
    
    public java.util.Calendar getLastChange() {
        return lastChange;
    }

    public void setLastChange(java.util.Calendar lastChange) {
        this.lastChange = lastChange;
    }

    public String toString() {
        return "User " + getName() +
               " pwd: " + getPassword() +
               " lastChange: " + DateFormat.getDateInstance().format(getLastChange().getTime());
    }
}
