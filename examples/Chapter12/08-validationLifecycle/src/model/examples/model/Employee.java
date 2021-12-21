package examples.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


@Entity
public class Employee {

    @NotNull
    @Id
    private Integer id;
    
    @NotNull
    @Size(max=40)
    private String name;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name="S_DATE")
    private Date startDate;

    @Min(groups=Remove.class, value=0)
    @Max(groups=Remove.class, value=0)
    @Column(name="VAC_DAYS")
    private long vacationDays;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getVacationDays() {
        return vacationDays;
    }
    
    public void setVacationDays(long vacationDays) {
        this.vacationDays = vacationDays;
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " vacation: " + getVacationDays();
    }
}
