package examples.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
public class Project {

    @EmbeddedId 
    private ProjectId id;
    
    @MapsId("dept")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="DEPT_NUM", referencedColumnName="NUM"),
             @JoinColumn(name="DEPT_CTRY", referencedColumnName="CTRY")})
    private Department department;

    public ProjectId getId() {
        return id;
    }
    public void setId(ProjectId id) {
        this.id = id;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department dept) {
        this.department = dept;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE")
    private Date endDate;

    public Project() {}
    public Project(Department dept) {
        this.department = dept;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public String toString() {
        return "Project id: " + getId() + " dept: " + getDepartment();
    }
}
