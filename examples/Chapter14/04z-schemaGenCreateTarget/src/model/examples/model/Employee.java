package examples.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    @Column(columnDefinition="VARCHAR(40)") 
    private String name;
    @Column(name="START_DATE", columnDefinition="DATE DEFAULT CURRENT_DATE") 
    private java.sql.Date startDate;
    @ManyToOne 
    @JoinColumn(name="MGR", columnDefinition="VARCHAR(40)")
    private Employee manager;


    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
