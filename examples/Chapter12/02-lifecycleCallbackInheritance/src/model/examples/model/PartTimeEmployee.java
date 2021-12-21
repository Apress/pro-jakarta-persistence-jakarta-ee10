package examples.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@EntityListeners({})
@Table(name="PT_EMP")
@DiscriminatorValue("3")
public class PartTimeEmployee extends CompanyEmployee {
    @Column(name="H_RATE")
    private float hourlyRate;

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    @PrePersist
    @PreUpdate
    public void verifyVacation() {
        System.out.println("PartTimeEmployee.verifyVacation called on employee: " + getId());
        //...
    }

    public String toString() {
        return "PartTimeEmployee id: " + getId() + " name: " + getName();
    }
}
