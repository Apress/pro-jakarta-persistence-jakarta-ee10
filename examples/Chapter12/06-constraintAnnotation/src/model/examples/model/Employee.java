package examples.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Employee {

    @Id
    @NotNull
    private Integer id;

    @NotNull(message="Employee name must be specified")
    @Size(max=40)
    private String name;
    
    // Number of vacation hours - Are taken in minimum increments of 2
    @Even
    @Column(name="VACN_HOURS") 
    private Integer vacationHours;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getVacationHours() {
        return vacationHours;
    }
    
    public void setVacationHours(Integer hours) {
        this.vacationHours = hours;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + ", vacationHours: " + getVacationHours(); 
    }
}
